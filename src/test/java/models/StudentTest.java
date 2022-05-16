package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void getName() {
        Student testStudent = setUpStudent();
        assertEquals("Kirk Franklin", testStudent.getName());
    }

    @Test
    void getEmail() {
        Student testStudent = setUpStudent();
        assertEquals("kirk@test.com", testStudent.getEmail());
    }

    @Test
    void getPhoneNumber() {
        Student testStudent = setUpStudent();
        assertEquals("0712345678", testStudent.getPhoneNumber());
    }

    @Test
    void getCourseId() {
        Student testStudent = setUpStudent();
        assertEquals(1, testStudent.getCourseId());
    }

    @Test
    void getEnrollmentDate() {
        Student testStudent = setUpStudent();
        assertEquals("Mon 16 May", testStudent.getEnrollmentDate());
    }


    //helper
    public Student setUpStudent(){
        Student testStudent = new Student("Kirk Franklin", "kirk@test.com", "0712345678", 1, "Mon 16 May");
         return testStudent;
    }
}