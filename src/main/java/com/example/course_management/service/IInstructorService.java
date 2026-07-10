package com.example.course_management.service;

import java.util.List;

import com.example.course_management.dto.instructor.InstructorDetail;
import com.example.course_management.dto.instructor.InstructorRequest;
import com.example.course_management.dto.instructor.InstructorResponse;

public interface IInstructorService {
    
    List<InstructorResponse> getAllInstructor();

    InstructorResponse getInstructorById(Long id);

    InstructorResponse createInstructor(InstructorRequest instructorRequest);

    InstructorResponse updateInstructor(Long id, InstructorRequest instructorRequest);

    InstructorResponse deleteInstructorById(Long id);

    List<InstructorDetail> getAllInstructorDetail();

}
