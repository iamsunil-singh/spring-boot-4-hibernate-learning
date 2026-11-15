package com.iamsunil.cruddemo;

import com.iamsunil.cruddemo.dao.StudentDAO;
import com.iamsunil.cruddemo.entity.Student;
import org.hibernate.resource.beans.container.internal.JpaCompliantLifecycleStrategy;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Clock;
import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return runner -> {
			//createStudent(studentDAO);
			createMultipleStudent(studentDAO);
			//readsStudent(studentDAO);
			// queryForSrudents(studentDAO);
//			quesyForStudentsByLastName(studentDAO);
			//updateStudent(studentDAO);
//		deleteStudent(studentDAO);
//			deleteAllStudents(studentDAO);
		};
	}

	private void deleteAllStudents(StudentDAO studentDAO) {
		int noOfStudentsDeleted=studentDAO.deleteAll();
		System.out.println("Deleted Students: "+noOfStudentsDeleted);
	}

	private void deleteStudent(StudentDAO studentDAO) {
		// Retreive student based on the id: primary key
		int studentId = 1;
		System.out.println("Getting student with id: "+ studentId);
	    studentDAO.delete(studentId);
	}

	private void updateStudent(StudentDAO studentDAO) {
		// Retreive student based on the id: primary key
		int studentId = 1;
		System.out.println("Getting student with id: "+ studentId);
		Student myStudent = studentDAO.findById(studentId);

		// change first name to "Raj"
		System.out.println("Updating student...");
		myStudent.setFirstName("Raj");

		// update the student
		studentDAO.update(myStudent);

		// display the updated student
		System.out.println(myStudent);
	}

	private void quesyForStudentsByLastName(StudentDAO studentDAO) {
		// get a list of students
		List<Student> theStudents =studentDAO.findByLastName("Malhotra");

		// display the list of students
		for(Student tempStudent:theStudents){
			System.out.println(tempStudent);
		}
	}

	private void queryForSrudents(StudentDAO studentDAO) {
		// get a list of students
        List<Student> theStudents =studentDAO.findAll();

		// display the list of students
		for(Student tempStudent:theStudents){
			System.out.println(tempStudent);
		}

	}

	private void readsStudent(StudentDAO studentDAO) {
		// Create a student object
		System.out.println("Creating new Student object ...");
		Student tempStudent = new Student("Raj", "Malhotra", "dev.rajmalhotra@app.com");

		//save the student
		System.out.println("Saving the student...");
		studentDAO.save(tempStudent);

		//display id of the saved student
		int theId= tempStudent.getId();
		System.out.println("Saved student. Generated id: " + theId);

		// retrieve student based on the id: primary key
		System.out.println("Retreving student with id: " + theId);
		Student myStudent = studentDAO.findById(theId);

		//display student
		System.out.println("Found the student: " + myStudent);
	}

	private void createMultipleStudent(StudentDAO studentDAO) {
		// create multiple students
		System.out.println("Creating 3 new Student objects ...");
		Student tempStudent1 = new Student("Sunil", "Singh", "dev.iamsunil@gmail.com");
		Student tempStudent2 = new Student("Sunil2", "Singh", "dev.iamsunil@gmail.com1");
		Student tempStudent3 = new Student("Sunil3", "Singh", "dev.iamsunil@gmail.com2");

		// save the student objects
		System.out.println("Saving the students ...");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);
	}

	private void createStudent(StudentDAO studentDAO) {
		// create the student object
		System.out.println("Creating new Student object ...");
		Student tempStudent = new Student("Sunil", "Singh", "dev.iamsunil@gmail.com");

		// save the student object
		System.out.println("Saving the student ...");
		studentDAO.save(tempStudent);

		// display the id of the saved student
		System.out.println("Saved Student. Generated id: " + tempStudent.getId());

	}

}
