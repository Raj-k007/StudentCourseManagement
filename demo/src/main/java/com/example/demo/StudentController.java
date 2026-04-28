package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService=studentService;
    }

    @GetMapping
    public List<Student> findAll(){
        return studentService.findAll();
    }

    @GetMapping("/{id}")
    public Student findById(@PathVariable Long id){
        return studentService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        Student st = studentService.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(st);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student){
        Student st = studentService.updateStudent(id,student);
        return ResponseEntity.ok(st);
    }

    @PostMapping("/{id}/course/{courseId}")
    public ResponseEntity<Student> enrolStudentInCourse(@PathVariable Long id, @PathVariable int courseId){
        Student st = studentService.enrolStudent(id, courseId);
        return ResponseEntity.status(HttpStatus.CREATED).body(st);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long id){
        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
