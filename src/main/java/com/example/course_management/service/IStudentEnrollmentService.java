package com.example.course_management.service;

import java.util.List;

import com.example.course_management.dto.student_enrollment.StudentEnrollmentRequest;
import com.example.course_management.dto.student_enrollment.StudentEnrollmentResponse;


public interface IStudentEnrollmentService {
    
    List<StudentEnrollmentResponse> getAllStudentEnrollment();

    StudentEnrollmentResponse getStudentEnrollmentById(Long id);

    StudentEnrollmentResponse createStudentEnrollment(StudentEnrollmentRequest studentEnrollmentRequest);

    StudentEnrollmentResponse updateStudentEnrollment(Long id, StudentEnrollmentRequest studentEnrollmentRequest);

    StudentEnrollmentResponse deleteStudentEnrollmentById(Long id);

}
