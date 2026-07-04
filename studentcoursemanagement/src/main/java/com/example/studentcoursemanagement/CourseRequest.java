package com.example.studentcoursemanagement;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class CourseRequest {
    @NotNull
    private int courseId;
    private String courseName;
    private String instructor;
    private long durationHrs;
}
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
class CourseResponse implements Serializable {
    private int courseId;
    private String courseName;
    private String instructor;
    private long durationHrs;
    private List<StudentResponse> students;
}
