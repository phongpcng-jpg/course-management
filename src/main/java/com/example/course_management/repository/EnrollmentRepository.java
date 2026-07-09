package com.example.course_management.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.course_management.model.Enrollment;


@Repository
public class EnrollmentRepository {

    private final List<Enrollment> enrollments = new ArrayList<>();

    public EnrollmentRepository() {
        initData();
    }
    
    private void initData() {

        enrollments.add(
            Enrollment.builder()
                    .id(1L)
                    .studentName("David")
                    .courseId(1L)
                    .build()
        );

        enrollments.add(
            Enrollment.builder()
                    .id(2L)
                    .studentName("Emma")
                    .courseId(2L)
                    .build()
        );

    }

}
