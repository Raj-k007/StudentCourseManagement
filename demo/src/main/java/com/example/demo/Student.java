package com.example.demo;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import jakarta.persistence.*;

import javax.annotation.processing.Generated;

@Entity
@Table(name="students")
public class Student {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name="Full_name",nullable = false)
    private String name;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private int age;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;
    public Course getCourse(){
        return course;
    }
    public void setCourse(Course course){
        this.course=course;
    }
    public Student(){}
    public Student( String name, String email, int age){
        this.age=age;
        this.email=email;
        this.name=name;
    }
    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
