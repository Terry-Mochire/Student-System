package Dao;

import models.Student;

import java.util.List;

public interface StudentDao {
    //Create
    void add (Student student);

    //Read
    List<Student> getAll();

    Student findByName(String name);

    Student findByCourseId(int courseId)

    //Update

    //Delete
    void deleteByName(String name);

    void clearAll();
}
