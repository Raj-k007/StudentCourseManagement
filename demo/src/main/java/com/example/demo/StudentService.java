package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }
    public List<Student> findAll(){
        return studentRepository.findAll();
    }

    public Student findById(Long id){
        return studentRepository.findById(id).orElseThrow(()-> new RuntimeException("Student not found with id"+ id));
    }

    public Student createStudent(Student student){
        if(studentRepository.existsByEmail(student.getEmail())) {
            throw new RuntimeException("Student already exists with id"+student.getId());
        }
        return studentRepository.save(student);
    }
    Student enrolStudent(Long id, int courseId){
        Student st = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Student not found"));
        st.setCourse(course);
        course.getStudents().add(st);
        return studentRepository.save(st);
    }
    public Student updateStudent(Long id, Student updated){
        Student existing = findById(id);
        existing.setName(updated.getName());
        existing.setEmail(updated.getEmail());
        existing.setAge(updated.getAge());
        return existing;
    }
    public void delete(Long id){
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found: " + id);
        }
        studentRepository.deleteById(id);
    }

//    public StudentService(StudentRepository studentRepository){
//        this.studentRepository=studentRepository;
//    }
//
//    public List<Student> findAll(){
//        return studentRepository.getStudents();
//    }
//
//    public Student findById(int id){
//        return studentRepository.getStudentById(id).orElseThrow(()-> new RuntimeException("Student not found"+id));
//    }
//
//    public  Student createStudent(Student student){
//        if(studentRepository.emailAlreadyExists(student.getEmail())){
//            throw new RuntimeException("Email already exists"+student.getEmail());
//        }
//        return studentRepository.save(student);
//    }
//
//    public Student updateStudent(int id, Student student){
//        return studentRepository.update(id, student).orElseThrow(()->new RuntimeException("Student is not present already to update"+ student));
//    }
//
//    public void delete(int id){
//        boolean deleted = studentRepository.delete(id);
//        if(!deleted){
//            throw new RuntimeException("Student with id "+id+" not found to delete");
//        }
//    }
}
