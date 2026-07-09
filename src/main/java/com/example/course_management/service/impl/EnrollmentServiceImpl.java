package com.example.course_management.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.course_management.repository.EnrollmentRepository;
import com.example.course_management.service.IEnrollmentService;


@Service
public class EnrollmentServiceImpl implements IEnrollmentService{

    private final EnrollmentRepository enrollmentRepository;

    @Autowired
    public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

}
