package com.example.course_management.service.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.course_management.dto.course.CourseSummary;
import com.example.course_management.enums.CourseStatus;
import com.example.course_management.repository.jpa.CourseRepository;
import com.example.course_management.repository.jpa.StudentEnrollmentRepository;
import com.example.course_management.service.IInstructorDetailService;


@Service
public class InstructorDetailServiceImpl implements IInstructorDetailService{

    private final StudentEnrollmentRepository studentEnrollmentRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public InstructorDetailServiceImpl(
        StudentEnrollmentRepository studentEnrollmentRepository,
        CourseRepository courseRepository
    ) {

        this.studentEnrollmentRepository = studentEnrollmentRepository;

        this.courseRepository = courseRepository;
    }

    @Override
    public Set<Long> getValidCourseIds() {
        return studentEnrollmentRepository.findAll().stream()
                    .map(
                        studentEnrollment -> studentEnrollment
                                                .getCourse()
                                                .getId()
                    )
                    .collect(Collectors.toSet());
    }

    @Override
    public List<CourseSummary> getSummaryOfAllActivateCoursesAndHaveAtLeastOneEnrollmentByInstructorId(Long instructorId) {
        
        Set<Long> validCourseIds = getValidCourseIds();

        return courseRepository.findAll().stream()
                .filter(course -> course.getInstructor().getId().equals(instructorId))
                .filter(course -> course.getStatus() == CourseStatus.ACTIVE)
                .filter(course -> validCourseIds.contains(course.getId()))
                .map(course -> CourseSummary
                        .builder()
                        .id(course.getId())
                        .title(course.getTitle())
                        .build()
                    )
                .toList();

    }

}
