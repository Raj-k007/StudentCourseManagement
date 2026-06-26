package com.example.demo;
import jakarta.validation.constraints.*;
import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class StudentRequest {
    @NotBlank(message = "Name is required")
    @Size(min=2, max=20)
    private String name;
    @NotBlank(message = "email is required")
    @Email(message = "should be in email valid format")
    private String email;
    @Min(18) @Max(100)
    private int age;

    private String courseId;

}

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class StudentResponse {
    private int id;
    private String name;
    private String email;
    private int age;
    private int courseId;
    private String courseName;
}