package Dao;

import models.Student;

import java.util.List;

public interface StudentDao {
    //Create
    void add (Student student);

    //Read
    List<Student> getAll();

    Student findById(int studentId);


    //Update

    //Delete
    void deleteById(int studentId);

    void clearAll();
}
