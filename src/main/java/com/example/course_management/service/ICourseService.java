package com.example.course_management.service;

import java.util.List;

import com.example.course_management.entity.Course;

public interface ICourseService {

    List<Course> getAllCourses();

    Course getCourseById(Long id);

    Course createCourse(Course course);

    Course updateCourse(Long id, Course course);

    Course deleteCourseById(Long id);

}
