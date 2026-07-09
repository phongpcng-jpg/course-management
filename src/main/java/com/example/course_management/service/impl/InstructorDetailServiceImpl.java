package com.example.course_management.service.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.course_management.dto.course.CourseSummary;
import com.example.course_management.enums.CourseStatus;
import com.example.course_management.repository.CourseRepository;
import com.example.course_management.repository.EnrollmentRepository;
import com.example.course_management.service.IInstructorDetailService;


@Service
public class InstructorDetailServiceImpl implements IInstructorDetailService{

    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public InstructorDetailServiceImpl(
        EnrollmentRepository enrollmentRepository,
        CourseRepository courseRepository
    ) {

        this.enrollmentRepository = enrollmentRepository;

        this.courseRepository = courseRepository;
    }

    @Override
    public Set<Long> getValidCourseIds() {
        return enrollmentRepository.findAll().stream()
                    .map(enrollment -> enrollment.getCourseId())
                    .collect(Collectors.toSet());
    }

    @Override
    public List<CourseSummary> getSummaryOfAllActivateCoursesAndHaveAtLeastOneEnrollmentByInstructorId(Long instructorId) {
        
        Set<Long> validCourseIds = getValidCourseIds();

        return courseRepository.findAll().stream()
                .filter(course -> course.getInstructorId().equals(instructorId))
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
