package Dao;

import Database.DB;
import models.Student;
import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Sql2oStudentDaoTest {
    Sql2oStudentDao sql2oStudentDao = new Sql2oStudentDao(DB.sql2o);

    @Rule
    public DatabaseRule databaseRule = new DatabaseRule();


    @AfterEach
    public void tearDown() {
        sql2oStudentDao.clearAll();
    }

    @Test
    public void newStudentInstantiatesCorrectly_true(){
        Student testStudent = setUpStudent();
        assertEquals(true, testStudent instanceof Student);
    }

    @Test
    void newStudentInstantiatesCorrectlyWithId() {
        Student testStudent = setUpStudent();
        assertEquals( 0, testStudent.getId());
    }

    @Test
    void add_insertsObjectIntoDatabase() {
        Student testStudent = setUpStudent();
        sql2oStudentDao.add(testStudent);
        assertTrue(sql2oStudentDao.getAll().get(0).equals(testStudent));
    }

    @Test
    void getAll_returnsAllInstancesOfStudent() {
        Student testStudent = setUpStudent();
        Student testStudent2 = setUpStudent();
        Student testStudent3 = setUpStudent();
        sql2oStudentDao.add(testStudent);
        sql2oStudentDao.add(testStudent2);
        sql2oStudentDao.add(testStudent3);
        assertTrue(sql2oStudentDao.getAll().get(0).equals(testStudent));
        assertTrue(sql2oStudentDao.getAll().get(1).equals(testStudent2));
        assertTrue(sql2oStudentDao.getAll().get(2).equals(testStudent3));
    }

    @Test
    void findByName_returnsStudentWithTheSameName() {
        Student testStudent = setUpStudent();
        Student testStudent2 = setUpStudent2();
        sql2oStudentDao.add(testStudent);
        sql2oStudentDao.add(testStudent2);
        Student savedStudent = sql2oStudentDao.findByName(testStudent.getName());
        assertEquals(testStudent.getName(), savedStudent.getName());
    }

    @Test
    void deleteById() {
    }

    @Test
    void clearAll() {
    }



    //helper
    public Student setUpStudent(){
        Student testStudent = new Student("Kirk", "kirk@test.com", "0712345678",  "2022-05-16", 1);
        return testStudent;
    }

    public Student setUpStudent2(){
        Student testStudent = new Student("Marilyn Monroe", "monroe@test.com", "0787654321",  "2022-04-23", 2);
        return testStudent;
    }


}