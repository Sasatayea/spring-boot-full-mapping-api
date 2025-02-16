package com.luv2code.cruddemo.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "instructor")
public class Instructor {

    // annotate the class as an entity and map to db table

    // define the field

    // annotate the fields with db column names

    // ** set up mapping to InstructorDetail entity

    // create constructor

    // create getters and setters

    // create toString method

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_Name")
    private String firstName;

    @Column(name = "last_Name")
    private String LastName;

    @Column(name = "email")
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instructor_detail_id")
    private InstructorDetail instructorDetail;

    @OneToMany( fetch = FetchType.LAZY,
            mappedBy = "instructor", cascade = {CascadeType.DETACH, CascadeType.PERSIST,
                                                CascadeType.MERGE, CascadeType.REFRESH})
    private List<Course> courses;

    public Instructor() {}

    public Instructor(String firstName, String lastName, String email) {
        this.firstName = firstName;
        LastName = lastName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public InstructorDetail getInstructorDetail() {
        return instructorDetail;
    }

    public void setInstructorDetail(InstructorDetail instructorDetail) {
        this.instructorDetail = instructorDetail;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    // add convenience methods for bi-directional relationship
    public void addCourse(Course tempCourse){

        if (courses == null){
            courses = new ArrayList<>();
        }
        courses.add(tempCourse);

        tempCourse.setInstructor(this);
    }


    @Override
    public String toString() {
        return "Instructor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", email='" + email + '\'' +
                ", instructorDetail=" + instructorDetail +
                '}';
    }
}
