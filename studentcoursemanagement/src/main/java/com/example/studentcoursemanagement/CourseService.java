package com.example.studentcoursemanagement;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseMapper courseMapper;
    private final CourseRepository courseRepository;

//    public CourseService(CourseRepository courseRepository){
//        this.courseRepository=courseRepository;
//    }
    @Cacheable(value="courses", key = "'all-courses'")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<CourseResponse> findAll(){
        return courseMapper.toList(courseRepository.findAll());
    }

    @Cacheable(value = "courses", key = "#id")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public CourseResponse findById(int id){
        Course course = courseRepository.findById(id).orElseThrow(()-> new RuntimeException("Course with course id "+id+" not found"));
        return courseMapper.toResponse(course);
    }
    @Transactional
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @CacheEvict(value = "courses", key= "'all-courses")
    public CourseResponse createCourse(CourseRequest request){
        Course course = courseMapper.toEntity(request);
        if(courseRepository.existsByCourseName(course.getCourseName())){
            throw new RuntimeException("Course with course id "+course.getCourseName()+" already found");
        }
        return courseMapper.toResponse(courseRepository.save(course));
    }
    @Transactional
    @CachePut(value = "courses", key = "#id")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public CourseResponse updateCourse(int id, CourseRequest request){
        Course existing = courseRepository.findById(id).orElseThrow(()-> new RuntimeException("Course with course id "+id+" not found"));
        if(!courseRepository.existsByCourseName(existing.getCourseName())) throw new RuntimeException("Course with course id "+id+" not found");
        courseMapper.updateCourse(request, existing);
        return courseMapper.toResponse(existing);
    }

    @Caching(evict = {
            @CacheEvict(value = "courses", key = "#id", beforeInvocation = false),
            @CacheEvict(value = "courses", key = "'all-courses'", beforeInvocation = false)
    })
    @Transactional
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void deleteCourse(int id){
        if(!courseRepository.existsById(id)){
            throw new RuntimeException("Course with course id "+id+" not found");
        }
        courseRepository.deleteById(id);
    }

    @Cacheable(value = "courses", key = "#courseName")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<CourseResponse> searchCourse(String courseName){
        return courseMapper.toList(courseRepository.findByCourseName(courseName));
    }
}
