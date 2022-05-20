SET MODE PostgreSQL;

CREATE DATABASE student_system;

CREATE TABLE  IF NOT EXISTS students(
	id serial PRIMARY KEY,
	name VARCHAR(200),
	email VARCHAR(250),
	phoneNumber VARCHAR(100),
	enrollmentDate timestamp,
	courseId int
);

CREATE TABLE IF NOT EXISTS courses {
    id serial PRIMARY KEY,
    	coursename VARCHAR(100),
    	coursedetails VARCHAR(300)
}