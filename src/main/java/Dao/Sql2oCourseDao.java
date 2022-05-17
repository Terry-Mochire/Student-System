package Dao;

import Database.DB;
import models.Course;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oCourseDao implements CourseDao{

    private final Sql2o sql2o;

    public Sql2oCourseDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }


    @Override
    public void add(Course course) {
        String sql = "INSERT INTO courses(coursename, coursedetails) VALUES (:courseName, :courseDetails);";
        try(Connection con =DB.sql2o.open()){
            int id = (int) con.createQuery(sql, true)
                    .bind(course)
                    .executeUpdate()
                    .getKey();
            course.setId(id);
        }catch (Sql2oException ex) {
            System.out.println("Error in adding course to database: " + ex);
        }
    }

    @Override
    public List<Course> getAll() {
        String sql = "SELECT * FROM courses;";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql).executeAndFetch(Course.class);
        }
    }

    @Override
    public Course findByName(String courseName) {
        String sql = "SELECT * FROM courses WHERE coursename = :courseName;";
        try(Connection con = DB.sql2o.open()) {
            return con.createQuery(sql)
                            .addParameter("courseName", courseName)
                    .executeAndFetchFirst(Course.class);
        }
    }

    @Override
    public void deleteByName(String name) {
        String sql = "DELETE FROM courses WHERE coursename = :name;";
        try(Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("name",name)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println("Error deleting this course" + ex);
        }
    }

    @Override
    public void clearAll() {
        String sql = "DELETE FROM courses";
        try(Connection con = DB.sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println("Error deleting all courses" + ex);
        }
    }
}
