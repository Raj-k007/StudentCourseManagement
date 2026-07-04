package com.example.studentcoursemanagement;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    @Mapping(source="course.courseId", target="courseId")
    @Mapping(source = "course.courseName", target = "courseName")
    StudentResponse toResponse(Student student);

    @Mapping(target = "course", ignore = true)
    Student toEntity(StudentRequest studentRequest);

    List<StudentResponse> toResponseList(List<Student> studentList);

    @Mapping(target = "course", ignore = true)
    Student updateEntity(StudentRequest request, @MappingTarget Student student);
}
