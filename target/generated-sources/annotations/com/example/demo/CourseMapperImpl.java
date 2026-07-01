package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-06-24T16:45:06+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 18.0.1.1 (Oracle Corporation)"
)
@Component
public class CourseMapperImpl implements CourseMapper {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Course toEntity(CourseRequest request) {
        if ( request == null ) {
            return null;
        }

        Course course = new Course();

        course.setCourseId( request.getCourseId() );
        course.setCourseName( request.getCourseName() );
        course.setInstructor( request.getInstructor() );
        course.setDurationHrs( request.getDurationHrs() );

        return course;
    }

    @Override
    public CourseResponse toResponse(Course course) {
        if ( course == null ) {
            return null;
        }

        CourseResponse.CourseResponseBuilder courseResponse = CourseResponse.builder();

        courseResponse.students( studentMapper.toResponseList( course.getStudents() ) );
        courseResponse.courseId( course.getCourseId() );
        courseResponse.courseName( course.getCourseName() );
        courseResponse.instructor( course.getInstructor() );
        courseResponse.durationHrs( course.getDurationHrs() );

        return courseResponse.build();
    }

    @Override
    public List<CourseResponse> toList(List<Course> courseList) {
        if ( courseList == null ) {
            return null;
        }

        List<CourseResponse> list = new ArrayList<CourseResponse>( courseList.size() );
        for ( Course course : courseList ) {
            list.add( toResponse( course ) );
        }

        return list;
    }

    @Override
    public Course updateCourse(CourseRequest request, Course existing) {
        if ( request == null ) {
            return existing;
        }

        existing.setCourseId( request.getCourseId() );
        existing.setCourseName( request.getCourseName() );
        existing.setInstructor( request.getInstructor() );
        existing.setDurationHrs( request.getDurationHrs() );

        return existing;
    }
}
