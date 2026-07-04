package com.example.studentcoursemanagement;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
//import org.springframework.cache.annotation.Caching;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final StudentMapper studentMapper;
    private final EmailService emailService;
//    public StudentService(StudentRepository studentRepository, CourseRepository courseRepository) {
//        this.studentRepository = studentRepository;
//        this.courseRepository = courseRepository;
//    }
    @Cacheable(value = "students", key = "'all-students'")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<StudentResponse> findAll(){

        return studentMapper.toResponseList(studentRepository.findAll());
    }

    @Cacheable(value = "students", key = "#id")
    @PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_ADMIN')")
    public StudentResponse findById(Long id){
        Student student = studentRepository.findById(id).orElseThrow(()-> new RuntimeException("Student not found with id"+ id));
        return studentMapper.toResponse(student);
    }

//    @Cacheable(value = "students", key = "#email")
//    StudentResponse findStudentByEmail(String email){
//        return studentMapper.toResponse(studentRepository.findByEmail(email));
//    }
    @Transactional
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @CacheEvict(value = "students", key= "'all-students'")
    public StudentResponse createStudent(StudentRequest request){
        Student student = studentMapper.toEntity(request);
        if(studentRepository.findByEmail(request.getEmail())!=null) {
            throw new RuntimeException("Student already exists with id"+student.getId());
        }
        Student savedStudent = studentRepository.save(student);
        return studentMapper.toResponse(savedStudent);
    }
    @Transactional
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @CacheEvict(value = "students", key= "'all-students'")
    StudentResponse enrolStudent(Long id, int courseId){
        Student st = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Student not found"));
        st.setCourse(course);
        course.getStudents().add(st);
        emailService.sendEmail(st.getEmail());
        return studentMapper.toResponse(studentRepository.save(st));
    }
    @Transactional
    @CachePut(value = "students", key="#id")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public StudentResponse updateStudent(Long id, StudentRequest request){
        Student existing = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found with id"+ id));
        studentMapper.updateEntity(request, existing);
        return studentMapper.toResponse(existing);
    }

    @Transactional
    @CacheEvict(value = "students", allEntries = true, beforeInvocation = false)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void delete(Long id){
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found: " + id);
        }
        studentRepository.deleteById(id);
//        try {
//            Thread.sleep(500);
//        }catch (InterruptedException e) {
//
//        }
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
