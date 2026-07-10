package com.example.course_management.dto.course;

import com.example.course_management.dto.instructor.InstructorResponse;
import com.example.course_management.enums.CourseStatus;

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
public class CourseInstructorResponse {

    private String title;

    private CourseStatus status;

    private InstructorResponse instructor;

}
