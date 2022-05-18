package models;

import java.util.Objects;

public class Course {
    private String courseName;

    private String courseDetails;
    private int id;

    public Course(String courseName, String courseDetails ){
        this.courseName = courseName;
        this.courseDetails = courseDetails;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDetails() {
        return courseDetails;
    }

    public void setCourseDetails(String courseDetails) {
        this.courseDetails = courseDetails;
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
        return id == course.id && courseName.equals(course.courseName) && courseDetails.equals(course.courseDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseName, courseDetails, id);
    }
}
