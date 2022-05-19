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
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567;
    }
    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        staticFileLocation("/public");
        //Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/student_system", "terry", "Postgres4041*");

        Sql2o sql2o = new Sql2o("jdbc:postgresql://ec2-52-3-2-245.compute-1.amazonaws.com:5432/d65d4k9v7tj9an", "olokzrhfenuuzy", "625b7d74bdc4aac854c13b0b59added35d1e7a2c1f3eb96fe078898881969281");

        Sql2oStudentDao sql2oStudentDao = new Sql2oStudentDao(sql2o);
        Sql2oCourseDao sql2oCourseDao = new Sql2oCourseDao(sql2o);

        get("/", (request, response) -> {

                    return new ModelAndView(new HashMap(), "index.hbs");
                    }, new HandlebarsTemplateEngine());

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
              response.redirect("/student/new");
              return null;
        }, new HandlebarsTemplateEngine());

        get("/courses", (request, response) -> {
            Map<String,Object> model = new HashMap<>();
            List<Student> students = sql2oStudentDao.getAll();
            List<Course> courses = sql2oCourseDao.getAll();
            model.put("students", students);
            model.put("courses", courses);
            return new ModelAndView(model, "course.hbs");
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