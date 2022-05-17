package Dao;

import models.Course;

import java.util.List;

public interface CourseDao {

    //Create
    void add(Course course);

    //Read
    List<Course> getAll();
    Course findByName(String name);

    //Update

    //Delete
    void deleteByName(String name);
    void clearAll();
}
