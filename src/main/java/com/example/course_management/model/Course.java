package com.example.course_management.model;

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
public class Course {

    private Long id;

    private String title;

    private CourseStatus status;

    private Long instructorId;

}