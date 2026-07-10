package com.example.course_management.service.legacy;

import java.util.List;
import java.util.Set;

import com.example.course_management.dto.course.CourseSummary;

public interface IInstructorDetailService {

    Set<Long> getValidCourseIds();

    List<CourseSummary> getSummaryOfAllActivateCoursesAndHaveAtLeastOneEnrollmentByInstructorId(Long instructorId);

}