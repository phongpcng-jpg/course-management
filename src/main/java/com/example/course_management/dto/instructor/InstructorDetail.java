package com.example.course_management.dto.instructor;

import java.util.List;

import com.example.course_management.dto.course.CourseDetail;
import com.example.course_management.dto.course.CourseSummary;

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
public class InstructorDetail {

    private Long id;

    private String name;

    private String email;

    private List<CourseSummary> courseSummaries;

}
