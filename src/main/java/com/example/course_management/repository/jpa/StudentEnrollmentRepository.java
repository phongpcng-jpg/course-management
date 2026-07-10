package com.example.course_management.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.course_management.entity.StudentEnrollment;

public interface StudentEnrollmentRepository 
        extends JpaRepository<StudentEnrollment, Long>{

}
