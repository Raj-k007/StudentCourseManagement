package com.example.demo;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;
    private final StudentService studentService;
    @Autowired
//    public CourseController(CourseService courseService){
//        this.courseService=courseService;
//    }

    @GetMapping
    public List<CourseResponse> getCourses(){
        return courseService.findAll();
    }

    @GetMapping("/{courseId}")
    public CourseResponse getCourseById(@PathVariable int courseId){
        return courseService.findById(courseId);
    }

    @PostMapping
    public ResponseEntity<CourseResponse> createCourse(@Valid @RequestBody CourseRequest request){
        CourseResponse response =  courseService.createCourse(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<CourseResponse> updateCourse(@PathVariable int courseId, @Valid @RequestBody CourseRequest request){
        CourseResponse response = courseService.updateCourse(courseId, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<Void> deleteCourse(@PathVariable int courseId){
        courseService.deleteCourse(courseId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(params = "courseName")
    public List<CourseResponse> search(@RequestParam String courseName){
        return courseService.searchCourse(courseName);
    }

}
