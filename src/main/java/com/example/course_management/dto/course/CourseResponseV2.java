package com.example.course_management.dto.course;

import com.example.course_management.enums.CourseStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponseV2 {

    private Long id;

    private String title;

    private CourseStatus status;
}
