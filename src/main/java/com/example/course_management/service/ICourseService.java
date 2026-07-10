package com.example.course_management.service;

import java.util.List;

import com.example.course_management.dto.course.CourseRequest;
import com.example.course_management.dto.course.CourseResponse;

public interface ICourseService {

    List<CourseResponse> getAllCourses();

    CourseResponse getCourseById(Long id);

    CourseResponse createCourse(CourseRequest courseRequest);

    CourseResponse updateCourse(Long id, CourseRequest courseRequest);

    CourseResponse deleteCourseById(Long id);

}
