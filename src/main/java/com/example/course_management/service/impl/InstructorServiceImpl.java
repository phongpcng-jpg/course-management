package com.example.course_management.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.course_management.repository.InstructorRepository;
import com.example.course_management.service.IInstructorService;


@Service
public class InstructorServiceImpl implements IInstructorService{

    private final InstructorRepository instructorRepository;

    @Autowired
    public InstructorServiceImpl(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

}
