package models;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

public class Student {
    private String name;
    private String email;
    private String phoneNumber;
    private int courseId;
    private Date enrollmentDate;

    private int id;


    public Student(String name, String email, String phoneNumber, String enrollmentDate, int courseId ){
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.courseId = courseId;
        this.enrollmentDate = Date.valueOf(enrollmentDate);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(String enrollmentDate) {
        this.enrollmentDate = Date.valueOf(enrollmentDate);
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
        Student student = (Student) o;
        return courseId == student.courseId && id == student.id && name.equals(student.name) && email.equals(student.email) && phoneNumber.equals(student.phoneNumber) && enrollmentDate.equals(student.enrollmentDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, phoneNumber, courseId, enrollmentDate, id);
    }
}
