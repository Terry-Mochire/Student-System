package models;

import java.util.Objects;

public class Course {
    private String courseName;
    private int id;

    public Course(String courseName){
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id == course.id && courseName.equals(course.courseName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseName, id);
    }
}
