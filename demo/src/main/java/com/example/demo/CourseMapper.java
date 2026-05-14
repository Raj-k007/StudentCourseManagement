package com.example.demo;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface CourseMapper {

    Course toEntity(CourseRequest request);

    @Mapping(source = "students", target = "students")
    CourseResponse toResponse(Course course);

    StudentResponse toResponse(Student student);
    List<CourseResponse> toList(List<Course> courseList);
    Course updateCourse(CourseRequest request, @MappingTarget Course existing);
}
