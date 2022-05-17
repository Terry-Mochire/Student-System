package Dao;

import Database.DB;
import models.Course;
import models.Student;
import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Sql2oCourseDaoTest {
    Sql2oCourseDao sql2oCourseDao = new Sql2oCourseDao(DB.sql2o);

    @Rule
    public DatabaseRule databaseRule = new DatabaseRule();

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        sql2oCourseDao.clearAll();
    }

    @Test
    void newCourseInstantiatesCorrectly(){
        Course testCourse = setUpCourse();
        assertEquals(true, testCourse instanceof Course);
    }

    @Test
    void newCourseInstantiatesCorrectlyWithId() {
        Course testCourse = setUpCourse();
        assertEquals( 0, testCourse.getId());
    }

    @Test
    void addInsertsObjectIntoDatabase() {
        Course testCourse = setUpCourse();
        sql2oCourseDao.add(testCourse);
        assertTrue(sql2oCourseDao.getAll().get(0).equals(testCourse));
    }

    @Test
    void getAll() {
    }

    @Test
    void findByName() {
    }

    @Test
    void deleteByName() {
    }


    //helper
    public Course setUpCourse(){
        Course testCourse = new Course("Communication", "Enhance your communication skills");
        return testCourse;
    }
}