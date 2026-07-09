package com.example.course_management.dto.enrollment;

import com.example.course_management.model.Course;

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
public class EnrollmentDetail {

    Long id;

    String studentName;

    Course course;

}
