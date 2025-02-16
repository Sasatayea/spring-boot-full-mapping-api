package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner (AppDAO appDAO){
		return runner -> {
//			createInstructor(appDAO);
//			findInstructor(appDAO);
//			delleteInstructor(appDAO);
//			findInstructorDetailsById(appDAO);
//			deleteInstructorDetails(appDAO);
//			createInstructorWithCourses(appDAO);
//			findInstructorWithCourses(appDAO);
//			findCoursesForInstructor(appDAO);
//			findInstructorWithCoursesJoinFetch(appDAO);
//			updateInstructor(appDAO);
//			updateCourse(appDAO);
//			delleteInstructor(appDAO);
//			delleteCourseById(appDAO);
//			createCourseAndReviews(appDAO);
//			retrieveCourseAndReviews(appDAO);
//			deleteCourseAndReviews(appDAO);
//			createCourseAndStudent(appDAO);
//			findCourseAndStudent(appDAO);
//			findStudentAndCourse(appDAO);
//			addMoreCourseForStudent(appDAO);
//			deleteCourseAndReviews(appDAO);
//			deleteStudentById(appDAO);


		};
	}

	private void deleteStudentById(AppDAO appDAO) {
		int theId = 2;
        appDAO.deleteStudentById(theId);
        System.out.println("Student with id " + theId + " deleted!");
	}

	private void addMoreCourseForStudent(AppDAO appDAO) {
		int theId = 2;
		Student tempStudent = appDAO.findCourseAndStudentByStudentId(theId);

		// create more courses
		Course course2 = new Course("Zelda - How to beat the game");
        Course course3 = new Course("Super Mario Bros. - How to get the highest score");

        // add courses to the student
        tempStudent.addCourse(course2);
        tempStudent.addCourse(course3);

        System.out.println("Saving Student: " + tempStudent);
        System.out.println("associated courses: " + tempStudent.getCourses());

		// save the student
		appDAO.update(tempStudent);
	}

	private void findStudentAndCourse(AppDAO appDAO) {
		// send student id and catch list of courses
        int theId = 2;

        Student TheStudent = appDAO.findCourseAndStudentByStudentId(theId);

        System.out.println("Student: " + TheStudent);
        System.out.println("Courses: "+ TheStudent.getCourses());
        System.out.println("Done!");
	}

	private void findCourseAndStudent(AppDAO appDAO) {
		// send course id and catch list of students
		int theId = 10;

		Course TheCourse = appDAO.findCourseAndStudentByCourseId(theId);

		System.out.println("Course: " + TheCourse);
		System.out.println("Students: "+ TheCourse.getStudents());
		System.out.println("Done!");
	}

	private void createCourseAndStudent(AppDAO appDAO) {
		// create a new course
		Course course = new Course("Pacman - How to score one million Points");

		// create a new student
		Student student = new Student("John", "Doe", "johndoe@example.com");
		Student student2 = new Student("Sasa", "salah", "memo@example.com");

        // create a new course and student relationship
        course.addStudent(student);
		course.addStudent(student2);

        // save the course and student

        appDAO.save(course);

        System.out.println("Course and Student saved successfully with id: " + course.getId() + ", " + student.getId());


	}

	private void deleteCourseAndReviews(AppDAO appDAO) {
		int theId = 10;

        appDAO.deleteCourseById(theId);

        System.out.println("Course and Reviews deleted successfully with id: " + theId);
	}

	private void retrieveCourseAndReviews(AppDAO appDAO) {
		int theId = 10;

        Course course = appDAO.findCourseAndReviewByCourseId(theId);

        System.out.println("Course with Reviews: " + course);
		System.out.println("Reviews: " + course.getReview());
	}

	private void createCourseAndReviews(AppDAO appDAO) {

		// create a new course
		Course course = new Course("Pacman - How to score one million Points");

		// add some reviews
		course.addReview(new Review("Great course ... love it!"));
		course.addReview(new Review("Course is easy to understand"));
		course.addReview(new Review("Course needs more practice"));

		// save the course

		appDAO.save(course);

        System.out.println("Course saved successfully with id: " + course.getId());
	}

	private void delleteCourseById(AppDAO appDAO) {
		int theId = 10;

		appDAO.deleteCourseById(theId);

        System.out.println("Course deleted successfully with id: " + theId);
	}

	private void updateCourse(AppDAO appDAO) {
		int theId = 11;

        Course tempCourse = appDAO.findCourseById(theId);

        System.out.println("Course: " + tempCourse);

        // update Course data
        tempCourse.setTitle("Spring Boot");

        appDAO.update(tempCourse);

        System.out.println("Course updated successfully!");
	}

	private void updateInstructor(AppDAO appDAO) {

		int theId = 1;

		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("Instruct: " + tempInstructor);

		// update Instructor data
 		tempInstructor.setFirstName("hala");

		appDAO.UpdateInstructor(tempInstructor);

		System.out.println("Instructor updated successfully!");
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {

		int theId = 1;

        System.out.println("Finding Instructor with Courses for Instructor Id: "+ theId);

        Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);

        System.out.println("The Instructor: "+ tempInstructor);

        System.out.println("the Courses: "+ tempInstructor.getCourses());

        System.out.println("Done!");
	}

	private void findCoursesForInstructor(AppDAO appDAO) {
		int theId = 1;
        System.out.println("Finding Courses for Instructor Id: "+ theId);

        Instructor tempInstructor = appDAO.findInstructorById(theId);

        System.out.println("The Instructor: "+ tempInstructor);

		// find Courses for Instructor
		System.out.println("Finding Courses forInstructor Id: "+ theId);

		List<Course> courses = appDAO.findCoursesBiInstructorId(theId);

		tempInstructor.setCourses(courses);

		System.out.println("the Courses" + tempInstructor.getCourses());

        System.out.println("Done!");
	}

	private void findInstructorWithCourses(AppDAO appDAO) {

		int theId = 1;
		System.out.println("Finding Instructor Id: "+ theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("TheInstructor: "+ tempInstructor);
		System.out.println("the Courses: "+ tempInstructor.getCourses());

		System.out.println("Done!");

	}

	private void createInstructorWithCourses(AppDAO appDAO) {

		// create the instructor
		Instructor tempInstructor = new Instructor("sasa", "Tayea","memo@gmail");

		// create he instructor details
		InstructorDetail instructorDetail = new InstructorDetail(
				"http://www.sasaTayea.com",
				"programming"
		);

		// associate the objects
		tempInstructor.setInstructorDetail(instructorDetail);

		// add courses to the instructor

		Course course1 = new Course("OPP");
		Course course2 = new Course("HTML");

		tempInstructor.addCourse(course1);
		tempInstructor.addCourse(course2);

		// save the instructor with courses
		// Note: this also saves the Course
		System.out.println("saving instructor: "+ tempInstructor);
		System.out.println("saving Courses: " +tempInstructor.getCourses());

		appDAO.save(tempInstructor);

		System.out.println("Done!");
	}

	private void deleteInstructorDetails(AppDAO appDAO) {
		int theId = 3;

        System.out.println("Deleting instructor details with id: " + theId);

        appDAO.deleteInstructorDetailById(theId);

        System.out.println("Deleted!");
	}

	private void findInstructorDetailsById(AppDAO appDAO) {
		int theId = 2;

        System.out.println("Fetching instructor details with id: " + theId);

        InstructorDetail tempInstructorDetails = appDAO.findInstructorDetailById(theId);

        System.out.println("tempInstructorDetails: " + tempInstructorDetails.getInstructor());
	}

	private void delleteInstructor(AppDAO appDAO) {

		int theId = 1;

		System.out.println("Deleting instructor id: " + theId);

        appDAO.deleteInstructorById(theId);

        System.out.println("Deleted!");
	}

	private void findInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Finding instructor id: " + theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("tempInstructor: " + tempInstructor);
//		System.out.println(" instructions: " + tempInstructor.getCourses());
	}

	private void createInstructor(AppDAO appDAO) {

		// create the instructor
		Instructor tempInstructor = new Instructor("sasa", "Tayea","memo@gmail");

		// create he instructor details
		InstructorDetail instructorDetail = new InstructorDetail(
				"http://www.sasaTayea.com",
				"programming"
		);

		// associate the objects
		tempInstructor.setInstructorDetail(instructorDetail);

		// save the instructor
		System.out.println("Saving instructor" + tempInstructor);
		appDAO.save(tempInstructor);

		System.out.println("Done!");

	}

}
