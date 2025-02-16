package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO{

    // define field for entity manager
    private EntityManager entityManager;

    // inject entity manager using constructor injection
    @Autowired
    public AppDAOImpl (EntityManager entityManager){
        this.entityManager = entityManager;
    }


    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        entityManager.persist(theInstructor);
    }

    @Override
    public Instructor findInstructorById(int theId) {
        return entityManager.find(Instructor.class , theId);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {

        Instructor tempInstructor = entityManager.find(Instructor.class, theId);

        // remove the associated object reference
        // get the courses

        List<Course> courses = tempInstructor.getCourses();

        // break the association of all courses for the Instructor
        for (Course course : courses){
            course.setInstructor(null);
        }

        // delete the instructor

        entityManager.remove(tempInstructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return entityManager.find(InstructorDetail.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {

        InstructorDetail instructorDetail = entityManager.find(InstructorDetail.class, theId);

        // remove the associated object reference
        // break bi-directional link
        //
        instructorDetail.getInstructor().setInstructorDetail(null);

        entityManager.remove(instructorDetail);
    }

    @Override
    public List<Course> findCoursesBiInstructorId(int theID) {

        // create query
        TypedQuery<Course> query = entityManager.createQuery(
                "from Course where instructor.id = :data ", Course.class
        );

        query.setParameter("data", theID);

        // execute query
        List<Course> courses = query.getResultList();

        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {

        // create query
        TypedQuery<Instructor> query = entityManager.createQuery(
                "select i from Instructor i " +
                        "join fetch i.courses " +
                        "join fetch i.instructorDetail "+
                        "where i.id = :data ", Instructor.class
        );

        query.setParameter("data", theId);

        // execute query
        Instructor instructor = query.getSingleResult();

        return instructor;

    }

    @Override
    @Transactional
    public void UpdateInstructor(Instructor theInstructor) {

        entityManager.merge(theInstructor);
    }

    @Override
    @Transactional
    public void update(Course theCourse) {
        entityManager.merge(theCourse);
    }

    @Override
    public Course findCourseById(int theId) {
        return entityManager.find(Course.class, theId);
    }

    @Override
    @Transactional
    public void deleteCourseById(int theId) {

        Course tempCourse = entityManager.find(Course.class, theId);

        // remove the associated object reference
        // break bi-directional link

        entityManager.remove(tempCourse);
    }

    @Override
    @Transactional
    public void save(Course theCourse) {
        entityManager.persist(theCourse);
    }

    @Override
    public Course findCourseAndReviewByCourseId(int theCourseId) {
        // create query
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c " +
                        "join fetch c.review " +
                        "where c.id = :data ", Course.class
        );

        query.setParameter("data", theCourseId);

        // execute query
        Course course = query.getSingleResult();

        return course;
    }

    @Override
    public Course findCourseAndStudentByCourseId(int theCourseId) {
        // create query
        TypedQuery<Course> query = entityManager.createQuery(
                "select c from Course c " +
                        "join fetch c.students " +
                        "where c.id = :data ", Course.class
        );

        query.setParameter("data", theCourseId);

        // execute query
        Course course = query.getSingleResult();

        return course;
    }

    @Override
    public Student findCourseAndStudentByStudentId(int theStudentId) {
        // create query
        TypedQuery<Student> query = entityManager.createQuery(
                "select s from Student s " +
                        "join fetch s.courses " +
                        "where s.id = :data ", Student.class
        );

        query.setParameter("data", theStudentId);

        // execute query
        Student student = query.getSingleResult();

        return student;
    }

    @Override
    @Transactional
    public void update(Student theStudent) {
        entityManager.merge(theStudent);
    }

    @Override
    @Transactional
    public void deleteStudentById(int theId) {

        // retrieve the student
        Student tempStudent = entityManager.find(Student.class, theId);

        // remove the associated object reference
        // break bi-directional link
        entityManager.remove(tempStudent);
    }
}
