package com.example.course_management.service;

import java.util.List;

// import com.example.course_management.dto.student_enrollment.StudentEnrollmentCourseRequest;
// import com.example.course_management.dto.student_enrollment.StudentEnrollmentDetail;
import com.example.course_management.entity.StudentEnrollment;

public interface IStudentEnrollmentService {
    
    List<StudentEnrollment> getAllStudentEnrollment();

    StudentEnrollment getStudentEnrollmentById(Long id);

    StudentEnrollment createStudentEnrollmentOld(StudentEnrollment studentEnrollment);

    // StudentEnrollmentDetail createStudentEnrollment(StudentEnrollmentCourseRequest studentEnrollmentRequest);

    StudentEnrollment updateEnrollmentOld(Long id, StudentEnrollment studentEnrollment);

    // StudentEnrollmentDetail updateStudentEnrollment(Long id, StudentEnrollmentCourseRequest studentEnrollmentRequest);

    StudentEnrollment deleteStudentEnrollmentById(Long id);

}
