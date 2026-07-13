package com.example.course_management.repository.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.course_management.entity.Course;
import com.example.course_management.enums.CourseStatus;

public interface CourseRepository 
        extends JpaRepository<Course, Long>
{

    @Query("""
        SELECT c
        FROM Course c
        WHERE c.status = :status
    """)
    Page<Course> findAllByStatus(
        @Param("status") CourseStatus status,
        Pageable pageable
    );

}
