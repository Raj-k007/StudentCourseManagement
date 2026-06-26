package com.example.demo;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class CourseRequest {
    @NotBlank
    private int courseId;
    private String courseName;
    private String instructor;
    private long durationHrs;
}
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
class CourseResponse {
    private int courseId;
    private String courseName;
    private String instructor;
    private long durationHrs;
    private List<StudentResponse> students;
}
