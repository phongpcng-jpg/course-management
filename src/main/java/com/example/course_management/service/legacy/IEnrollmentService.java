package com.example.course_management.service.legacy;

import java.util.List;

import com.example.course_management.dto.enrollment.EnrollCourseRequest;
import com.example.course_management.dto.enrollment.EnrollmentDetail;
import com.example.course_management.model.Enrollment;

public interface IEnrollmentService {
    
    List<Enrollment> getAllEnrollment();

    Enrollment getEnrollmentById(Long id);

    Enrollment createEnrollmentOld(Enrollment enrollment);

    EnrollmentDetail createEnrollment(EnrollCourseRequest enrollmentRequest);

    Enrollment updateEnrollmentOld(Long id, Enrollment enrollment);

    EnrollmentDetail updateEnrollment(Long id, EnrollCourseRequest enrollmentRequest);

    Enrollment deleteEnrollmentById(Long id);

}
