import static spark.Spark.*;

import Dao.Sql2oStudentDao;
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
        staticFileLocation("/public");

        Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/student_system", "terry", "Postgres4041*");

        Sql2oStudentDao sql2oStudentDao = new Sql2oStudentDao(sql2o);

        get("/", (request, response) -> {
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

        get("/students", (request, response) -> {
            Map<String,Object> model = new HashMap<>();
            List<Student> students = sql2oStudentDao.getAll();
            model.put("students", students);
            return new ModelAndView(model, "studentform.hbs");
        }, new HandlebarsTemplateEngine());
    }
}