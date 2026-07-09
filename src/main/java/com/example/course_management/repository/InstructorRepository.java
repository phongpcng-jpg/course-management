package com.example.course_management.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.course_management.model.Instructor;


@Repository
public class InstructorRepository {

    private final List<Instructor> instructors = new ArrayList<>();

    public InstructorRepository() {
        initData();
    }
    
    private void initData() {

        instructors.add(
            Instructor.builder()
                    .id(1L)
                    .name("John Smith")
                    .email("john@gmail.com")
                    .build()
        );
        
        instructors.add(
            Instructor.builder()
                    .id(2L)
                    .name("Alice Brown")
                    .email("alice@gmail.com")
                    .build()
        );

    }

}
