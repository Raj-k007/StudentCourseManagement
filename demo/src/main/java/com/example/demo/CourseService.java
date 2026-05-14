package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository){
        this.courseRepository=courseRepository;
    }

    public List<Course> findAll(){
        return courseRepository.findAll();
    }

    public Course findById(int id){
        return courseRepository.findById(id).orElseThrow(()-> new RuntimeException("Course with course id "+id+" not found"));
    }

    public Course createCourse(Course course){
        if(courseRepository.existsByCourseName(course.getCourseName())){
            throw new RuntimeException("Course with course id "+course.getCourseName()+" already found");
        }
        return courseRepository.save(course);
    }

    public Course updateCourse(int id, Course course){
        if(!courseRepository.existsByCourseName(course.getCourseName())) throw new RuntimeException("Course with course id "+id+" not found");
        return courseRepository.save(course);
    }

    public void deleteCourse(int id){
        if(!courseRepository.existsById(id)){
            throw new RuntimeException("Course with course id "+id+" not found");
        }
        courseRepository.deleteById(id);
    }

    public List<Course> searchCourse(String courseName){
        return courseRepository.findByCourseName(courseName);
    }
}
