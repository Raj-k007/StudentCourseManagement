package com.example.demo;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

//    public StudentController(StudentService studentService){
//        this.studentService=studentService;
//    }

    @GetMapping
    public List<StudentResponse> findAll(){
        return studentService.findAll();
    }

    @GetMapping("/{id}")
    public StudentResponse findById(@PathVariable Long id){
        return studentService.findById(id);
    }

    @PostMapping
    public ResponseEntity<StudentResponse> createStudent(@Valid @RequestBody StudentRequest request){
        StudentResponse response = studentService.createStudent(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponse> updateStudent(@PathVariable Long id, @Valid @RequestBody StudentRequest request){
        StudentResponse response = studentService.updateStudent(id,request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/course/{courseId}")
    public ResponseEntity<StudentResponse> enrolStudentInCourse(@PathVariable Long id, @PathVariable int courseId){
        StudentResponse response = studentService.enrolStudent(id, courseId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StudentResponse> deleteStudent(@PathVariable Long id){
        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
