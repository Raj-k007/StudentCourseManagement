package com.example.demo;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
//@Repository
//public class StudentRepository {
//    private final List<Student> studentList = new ArrayList<>();
//    private int idCounter =1;
//    StudentRepository(){
//        studentList.add(new Student(idCounter++,"Raju","raj.new@gmail.com",22));
//        studentList.add(new Student(idCounter++,"Ravi","ravi.new@gmail.com",23));
//    }
//    public List<Student> getStudents(){
//        return new ArrayList<>(studentList);
//    }
//    public Optional<Student> getStudentById(int id){
//        return studentList.stream().filter(s->s.getId()==id).findFirst();
//    }
//    public Student save(Student student){
//        student.setId(idCounter++);
//        studentList.add(student);
//        return student;
//    }
//    public Optional<Student> update(int id, Student student){
//        return getStudentById(id).map(exist->{
//            exist.setId(student.getId());
//            exist.setAge(student.getAge());
//            exist.setEmail(student.getEmail());
//            exist.setName(student.getName());
//            return exist;
//        });
//    }
//    public boolean delete(int id){
//        return studentList.removeIf(s -> s.getId()==id);
//    }
//    public boolean emailAlreadyExists(String email){
//        return studentList.stream().anyMatch(s -> s.getEmail().equals(email));
//    }
//}
import org.springframework.data.jpa.repository.JpaRepository;
interface StudentRepository extends JpaRepository<Student,Long>{
    Student findDistinctByAge(int age, Sort sort);
    boolean existsByEmail(String email);

}
