import static spark.Spark.*;

import Dao.Sql2oCourseDao;
import Dao.Sql2oStudentDao;
import models.Course;
import models.Student;
import org.sql2o.Sql2o;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.ModelAndView;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        port(8080);
        staticFileLocation("/public");

        Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/student_system", "terry", "Postgres4041*");

        Sql2oStudentDao sql2oStudentDao = new Sql2oStudentDao(sql2o);
        Sql2oCourseDao sql2oCourseDao = new Sql2oCourseDao(sql2o);
        get("/student/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "studentform.hbs");
        }, new HandlebarsTemplateEngine());

        post("/students/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String email = request.queryParams("email");
            String phonenumber = request.queryParams("phonenumber");
            String enrolmentdate = String.valueOf(Date.valueOf(request.queryParams("enrolmentdate")));
            Integer courseid = Integer.parseInt(request.queryParams("courseid"));

            Student createdStudent = new Student(name, email, phonenumber, enrolmentdate, courseid);
              sql2oStudentDao.add(createdStudent);
            System.out.println(sql2oStudentDao.getAll());
              response.redirect("/");
              return null;
        }, new HandlebarsTemplateEngine());

        get("/courses", (request, response) -> {
            Map<String,Object> model = new HashMap<>();
            List<Student> students = sql2oStudentDao.getAll();
            List<Course> courses = sql2oCourseDao.getAll();
            model.put("students", students);
            model.put("courses", courses);
            return new ModelAndView(model, "courses.hbs");
        }, new HandlebarsTemplateEngine());

        //show all students in a course

        get("/students/:courseId", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int courseId = Integer.parseInt(request.params("courseId"));
            List<Student> studentsByCourse = sql2oStudentDao.findByCourseId(courseId);
            System.out.println(studentsByCourse);
            model.put("studentsByCourse", studentsByCourse);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());
    }
}