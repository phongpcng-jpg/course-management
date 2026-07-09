package com.example.course_management.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.course_management.enums.CourseStatus;
import com.example.course_management.model.Course;


@Repository
public class CourseRepository {

    private final List<Course> courses = new ArrayList<>();

    public CourseRepository() {
        initData();
    }
    
    private void initData() {

        courses.add(
            Course.builder()
                    .id(1L)
                    .title("Spring Boot")
                    .status(CourseStatus.ACTIVE)
                    .instructorId(1L)
                    .build()
        );

        courses.add(
            Course.builder()
                    .id(2L)
                    .title("AngularJS")
                    .status(CourseStatus.ACTIVE)
                    .instructorId(2L)
                    .build()
        );

    }

}
