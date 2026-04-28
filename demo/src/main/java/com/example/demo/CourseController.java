package com.example.demo;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

import java.util.List;

@RestController
@RequestMapping("/Courses")
public class CourseController {
    private final CourseService courseService;
    @Autowired
    public CourseController(CourseService courseService){
        this.courseService=courseService;
    }

    @GetMapping
    public List<Course> getCourses(){
        return courseService.findAll();
    }

    @GetMapping("/{courseId}")
    public Course getCourseById(@PathVariable int courseId){
        return courseService.findById(courseId);
    }

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course){
        Course created =  courseService.createCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<Course> updateCourse(@PathVariable int courseId, @RequestBody Course course){
        Course updated = courseService.updateCourse(courseId, course);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<Void> deleteCourse(@PathVariable int courseId){
        courseService.deleteCourse(courseId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(params = "courseName")
    public List<Course> search(@RequestParam String courseName){
        return courseService.searchCourse(courseName);
    }

}
