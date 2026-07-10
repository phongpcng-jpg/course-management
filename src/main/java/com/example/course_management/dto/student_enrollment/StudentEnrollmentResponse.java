package com.example.course_management.dto.student_enrollment;

import com.example.course_management.dto.course.CourseResponse;
import com.example.course_management.dto.student.StudentResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentEnrollmentResponse {

    private Long id;

    private StudentResponse student;

    private CourseResponse course;

}
