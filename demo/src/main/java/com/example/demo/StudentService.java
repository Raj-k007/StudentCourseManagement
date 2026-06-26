package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final StudentMapper studentMapper;
//    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository) {
//        this.studentRepository = studentRepository;
//        this.courseRepository = courseRepository;
//    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<StudentResponse> findAll(){

        return studentMapper.toResponseList(studentRepository.findAll());
    }
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public StudentResponse findById(Long id){
        Student student = studentRepository.findById(id).orElseThrow(()-> new RuntimeException("Student not found with id"+ id));
        return studentMapper.toResponse(student);
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public StudentResponse createStudent(StudentRequest request){
        Student student = studentMapper.toEntity(request);
        if(studentRepository.existsByEmail(student.getEmail())) {
            throw new RuntimeException("Student already exists with id"+student.getId());
        }
        Student savedStudent = studentRepository.save(student);
        return studentMapper.toResponse(savedStudent);
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    StudentResponse enrolStudent(Long id, int courseId){
        Student st = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Student not found"));
        st.setCourse(course);
        course.getStudents().add(st);
        return studentMapper.toResponse(studentRepository.save(st));
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public StudentResponse updateStudent(Long id, StudentRequest request){
        Student existing = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found with id"+ id));
        studentMapper.updateEntity(request, existing);
        return studentMapper.toResponse(existing);
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
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
