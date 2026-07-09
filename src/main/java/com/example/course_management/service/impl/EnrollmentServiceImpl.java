package com.example.course_management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.course_management.dto.enrollment.EnrollCourseRequest;
import com.example.course_management.dto.enrollment.EnrollmentDetail;
import com.example.course_management.enums.CourseStatus;
import com.example.course_management.model.Course;
import com.example.course_management.model.Enrollment;
import com.example.course_management.repository.EnrollmentRepository;
import com.example.course_management.service.ICourseService;
import com.example.course_management.service.IEnrollmentService;
import com.example.course_management.service.IInstructorService;


@Service
public class EnrollmentServiceImpl implements IEnrollmentService{

    private final EnrollmentRepository enrollmentRepository;
    private final ICourseService courseService;
    private final IInstructorService instructorService;

    @Autowired
    public EnrollmentServiceImpl(
        EnrollmentRepository enrollmentRepository,
        ICourseService courseService,
        IInstructorService instructorService
    ) {

        this.enrollmentRepository = enrollmentRepository;
        this.courseService = courseService;
        this.instructorService = instructorService;

    }

    @Override
    public List<Enrollment> getAllEnrollment() {
        return enrollmentRepository.findAll();
    }

    @Override
    public Enrollment getEnrollmentById(Long id) {
        return enrollmentRepository.findById(id)
                .orElseThrow(
                    () -> new RuntimeException(
                        "Enrollment not found."
                    )
                );
    }

    @Override
    public Enrollment createEnrollmentOld(Enrollment enrollment) {

        Course course;

        try {
            course = courseService.getCourseById(enrollment.getCourseId());
        } catch (RuntimeException e) {
            throw new RuntimeException(
                "Course of enrollment not found."
            );
        }

        if (course.getStatus() != CourseStatus.ACTIVE) {
            throw new RuntimeException(
                "Course is not active."
            );
        }
        
        try {
            instructorService.getInstructorById(
                course.getInstructorId()
            );
        } catch (RuntimeException e) {
            throw new RuntimeException(
                "Instructor of enrollment's course not found."
            );
        }

        return enrollmentRepository.create(enrollment);

    }

    @Override
    public EnrollmentDetail createEnrollment(
        EnrollCourseRequest enrollmentRequest) {

        Enrollment enrollment = Enrollment.builder()
                    .studentName(enrollmentRequest.getStudentName())
                    .courseId(enrollmentRequest.getCourseId())
                    .build();

        Course course;

        try {
            course = courseService.getCourseById(enrollment.getCourseId());
        } catch (RuntimeException e) {
            throw new RuntimeException(
                "Course of enrollment not found."
            );
        }

        if (course.getStatus() != CourseStatus.ACTIVE) {
            throw new RuntimeException(
                "Course is not active."
            );
        }
        
        try {
            instructorService.getInstructorById(
                course.getInstructorId()
            );
        } catch (RuntimeException e) {
            throw new RuntimeException(
                "Instructor of enrollment's course not found."
            );
        }

        Enrollment createdEnrollment =  enrollmentRepository.create(enrollment);

        try {
            return EnrollmentDetail.builder()
                    .id(createdEnrollment.getId())
                    .studentName(createdEnrollment.getStudentName())
                    .course(courseService.getCourseById(
                        createdEnrollment.getCourseId()
                    )).build();
        } catch (RuntimeException e) {
            throw new RuntimeException(
                "Course of enrollment not found after created."
            );
        }

    }

    @Override
    public Enrollment updateEnrollmentOld(Long id, Enrollment enrollment) {

        Course course;

        try {
            course = courseService.getCourseById(enrollment.getCourseId());
        } catch (RuntimeException e) {
            throw new RuntimeException(
                "Course of enrollment not found."
            );
        }

        if (course.getStatus() != CourseStatus.ACTIVE) {
            throw new RuntimeException(
                "Course is not active."
            );
        }
        
        try {
            instructorService.getInstructorById(
                course.getInstructorId()
            );
        } catch (RuntimeException e) {
            throw new RuntimeException(
                "Instructor of enrollment's course not found."
            );
        }

        return enrollmentRepository.update(id, enrollment)
                .orElseThrow(
                    () -> new RuntimeException(
                        "Enrollment not found."
                    )
                );

    }

    @Override
    public EnrollmentDetail updateEnrollment(
        Long id, 
        EnrollCourseRequest enrollmentRequest) {

        Enrollment enrollment = Enrollment.builder()
                    .studentName(enrollmentRequest.getStudentName())
                    .courseId(enrollmentRequest.getCourseId())
                    .build();

        Course course;

        try {
            course = courseService.getCourseById(enrollment.getCourseId());
        } catch (RuntimeException e) {
            throw new RuntimeException(
                "Course of enrollment not found."
            );
        }

        if (course.getStatus() != CourseStatus.ACTIVE) {
            throw new RuntimeException(
                "Course is not active."
            );
        }
        
        try {
            instructorService.getInstructorById(
                course.getInstructorId()
            );
        } catch (RuntimeException e) {
            throw new RuntimeException(
                "Instructor of enrollment's course not found."
            );
        }

        Enrollment updatedEnrollment =  enrollmentRepository.update(id, enrollment)
                .orElseThrow(
                    () -> new RuntimeException(
                        "Enrollment not found."
                    )
                );
        
        try {
            return EnrollmentDetail.builder()
                    .id(updatedEnrollment.getId())
                    .studentName(updatedEnrollment.getStudentName())
                    .course(courseService.getCourseById(
                        updatedEnrollment.getCourseId()
                    )).build();
        } catch (RuntimeException e) {
            throw new RuntimeException(
                "Course of enrollment not found after updated."
            );
        }

    }

    @Override
    public Enrollment deleteEnrollmentById(Long id) {
        return enrollmentRepository.deleteById(id)
                .orElseThrow(
                    () -> new RuntimeException(
                        "Enrollment not found."
                    )
                );
    }

}
