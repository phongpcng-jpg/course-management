package com.example.course_management.dto.course;

import java.util.List;

import com.example.course_management.enums.CourseStatus;
import com.example.course_management.model.Enrollment;

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
public class CourseSummary {

    private Long id;

    private String title;

}
