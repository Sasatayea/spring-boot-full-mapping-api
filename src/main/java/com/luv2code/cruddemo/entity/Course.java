package com.luv2code.cruddemo.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course")
public class Course {

    // define our fields

    // define constructor

    // define getters and setters

    // define toString method

    // annotate fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.PERSIST,
                          CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;


    @OneToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private List<Review> review;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.PERSIST,
                       CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "course_student",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List <Student> students;

    public Course(){}

    public Course(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public List<Review> getReview() {
        return review;
    }

    public void setReview(List<Review> review) {
        this.review = review;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    // add a convenience method
    public void addReview(Review tempReview) {
        if (review == null) {
            review = new ArrayList<>();
        }
        review.add(tempReview);
    }

    // add a convenience method
    public void addStudent(Student tempStudent) {
        if (students == null) {
            students = new ArrayList<>();
        }
        students.add(tempStudent);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
