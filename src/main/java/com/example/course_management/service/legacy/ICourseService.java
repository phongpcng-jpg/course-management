package com.example.course_management.service.legacy;

import java.util.List;

import com.example.course_management.model.Course;

public interface ICourseService {

    List<Course> getAllCourses();

    Course getCourseById(Long id);

    Course createCourse(Course course);

    Course updateCourse(Long id, Course course);

    Course deleteCourseById(Long id);

}
