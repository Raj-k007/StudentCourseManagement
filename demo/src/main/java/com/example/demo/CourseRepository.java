package com.example.demo;

import jakarta.annotation.PostConstruct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

interface CourseRepository extends JpaRepository<Course, Integer>{
    List<Course> findBycourseName(String courseName);

    Boolean existsByCourseName(String courseName);

    boolean existsBycourseName(String courseName);
}

//@Repository
//public class CourseRepository {
//    private final List<Course> courseList = new ArrayList<>();
//    private int courseId=2;
//
//    public CourseRepository(){
//    }
//    @PostConstruct
//    public void init(){
//        courseList.add(new Course(1,"Java","Ganesh",5));
//        courseList.add(new Course(2,"SQL","Ganesh",4));
//    }
//
//    public List<Course> findAll(){
//        return new ArrayList<>(courseList);
//    }
//
//    public Optional<Course> findById(int courseId){
//        return courseList.stream().filter(s->s.getCourseId()==courseId).findFirst();
//    }
//
//    public Course save(Course course){
//        course.setCourseId(++courseId);
//        courseList.add(course);
//        return course;
//    }
//
//    public Optional<Course> update(int id, Course course) {
//        return findById(id).map(existing -> {
//            existing.setCourseId(course.getCourseId());
//            existing.setCourseName(course.getCourseName());
//            existing.setInstructor(course.getInstructor());
//            existing.setDurationHrs(course.getDurationHrs());
//            return existing;
//        });
//    }
//
//    public boolean delete(int id) {
//        return courseList.removeIf(s -> s.getCourseId()==id);
//    }
//
//    public List<Course> search(String courseName){
//         return courseList.stream().filter(s-> s.getCourseName().equalsIgnoreCase(courseName)).collect(Collectors.toList());
//    }
//
//    public boolean courseExistsByName(String courseName){
//        return courseList.stream().anyMatch(s-> s.getCourseName().equalsIgnoreCase(courseName));
//    }
//}
