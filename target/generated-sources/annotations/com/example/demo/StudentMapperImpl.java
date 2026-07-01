package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-24T16:45:06+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 18.0.1.1 (Oracle Corporation)"
)
@Component
public class StudentMapperImpl implements StudentMapper {

    @Override
    public StudentResponse toResponse(Student student) {
        if ( student == null ) {
            return null;
        }

        StudentResponse.StudentResponseBuilder studentResponse = StudentResponse.builder();

        studentResponse.courseId( studentCourseCourseId( student ) );
        studentResponse.courseName( studentCourseCourseName( student ) );
        if ( student.getId() != null ) {
            studentResponse.id( student.getId().intValue() );
        }
        studentResponse.name( student.getName() );
        studentResponse.email( student.getEmail() );
        studentResponse.age( student.getAge() );

        return studentResponse.build();
    }

    @Override
    public Student toEntity(StudentRequest studentRequest) {
        if ( studentRequest == null ) {
            return null;
        }

        Student student = new Student();

        student.setName( studentRequest.getName() );
        student.setEmail( studentRequest.getEmail() );
        student.setAge( studentRequest.getAge() );

        return student;
    }

    @Override
    public List<StudentResponse> toResponseList(List<Student> studentList) {
        if ( studentList == null ) {
            return null;
        }

        List<StudentResponse> list = new ArrayList<StudentResponse>( studentList.size() );
        for ( Student student : studentList ) {
            list.add( toResponse( student ) );
        }

        return list;
    }

    @Override
    public Student updateEntity(StudentRequest request, Student student) {
        if ( request == null ) {
            return student;
        }

        student.setName( request.getName() );
        student.setEmail( request.getEmail() );
        student.setAge( request.getAge() );

        return student;
    }

    private int studentCourseCourseId(Student student) {
        if ( student == null ) {
            return 0;
        }
        Course course = student.getCourse();
        if ( course == null ) {
            return 0;
        }
        int courseId = course.getCourseId();
        return courseId;
    }

    private String studentCourseCourseName(Student student) {
        if ( student == null ) {
            return null;
        }
        Course course = student.getCourse();
        if ( course == null ) {
            return null;
        }
        String courseName = course.getCourseName();
        if ( courseName == null ) {
            return null;
        }
        return courseName;
    }
}
