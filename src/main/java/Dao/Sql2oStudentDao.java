package Dao;

import Database.DB;
import models.Student;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oStudentDao implements StudentDao{

    private final Sql2o sql2o;

    public Sql2oStudentDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Student student) {
        String sql = "INSERT INTO students (name, email, phonenumber, enrollmentdate, courseid) VALUES (:name, :email, :phoneNumber, :enrollmentDate, :courseId);";
        try(Connection con = DB.sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(student)
                    .executeUpdate()
                    .getKey();
            student.setId(id);
        }catch (Sql2oException ex){
            System.out.println("Error in creating student in Database" + ex);
        }
    }

    @Override
    public List<Student> getAll() {
        return null;
    }

    @Override
    public Student findById(int studentId) {
        return null;
    }

    @Override
    public void deleteById(int studentId) {

    }

    @Override
    public void clearAll() {
        String sql = "DELETE FROM students *;";
        try(Connection con = DB.sql2o.open()) {
            con.createQuery(sql).executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println("Error deleting students" + ex);
        }
    }
}
