package com.example.demo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Course {
    @Id
    @Column(nullable = false,unique = true)
    private int courseId;
    @Column(nullable = false)
    private String courseName;
    private String instructor;
    private long durationHrs;
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Student> students;
//    public Course(){}
//    public Course(int courseId, String courseName, String instructor, long durationHrs) {
//        this.courseId = courseId;
//        this.courseName = courseName;
//        this.instructor = instructor;
//        this.durationHrs = durationHrs;
//    }
//    public List<Student> getStudents(){
//        return this.students;
//    }
//    public int getCourseId() {
//        return courseId;
//    }
//
//    public void setCourseId(int courseId) {
//        this.courseId = courseId;
//    }
//
//    public String getCourseName() {
//        return courseName;
//    }
//
//    public void setCourseName(String courseName) {
//        this.courseName = courseName;
//    }
//
//    public String getInstructor() {
//        return instructor;
//    }
//
//    public void setInstructor(String instructor) {
//        this.instructor = instructor;
//    }
//
//    public long getDurationHrs() {
//        return durationHrs;
//    }
//
//    public void setDurationHrs(long durationHrs) {
//        this.durationHrs = durationHrs;
//    }
}
