package models;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getCourseName() {
        Course testCourse = setUpCourse();
        assertEquals("Communication", testCourse.getCourseName());
    }


    @Test
    void testEquals() {
        Course testCourse = setUpCourse();
        Course testCourse2 = setUpCourse();
        assertTrue(testCourse.equals(testCourse2));
    }

    //helper
    public Course setUpCourse(){
        Course testCourse = new Course("Communication");
        return testCourse;
    }
}