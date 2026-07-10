package com.example.course_management.service.legacy.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.example.course_management.model.Course;
import com.example.course_management.repository.CourseRepository;
import com.example.course_management.service.legacy.ICourseService;
import com.example.course_management.service.legacy.IInstructorService;


// @Repository
// @Profile("old")
public class CourseServiceImpl implements ICourseService{

    private final CourseRepository courseRepository;
    private final IInstructorService instructorService;

    @Autowired
    public CourseServiceImpl(
        CourseRepository courseRepository,
        IInstructorService instructorService
    ) {

        this.courseRepository = courseRepository;

        this.instructorService = instructorService;

    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(
                    () -> new RuntimeException(
                        "Course not found."
                    )
                );
    }

    @Override
    public Course createCourse(Course course) {

        try {
            instructorService.getInstructorById(
                course.getInstructorId()
            );
        } catch (RuntimeException e) {
            throw new RuntimeException(
                "Instructor of course not found."
            );
        }

        return courseRepository.create(course);
        
    }

    @Override
    public Course updateCourse(Long id, Course course) {
        
        try {
            instructorService.getInstructorById(
                course.getInstructorId()
            );
        } catch (RuntimeException e) {
            throw new RuntimeException(
                "Instructor of course not found."
            );
        }

        return courseRepository.update(id, course)
                .orElseThrow(
                    () -> new RuntimeException(
                        "Course not found."
                    )
                );

    }

    @Override
    public Course deleteCourseById(Long id) {
        return courseRepository.deleteById(id)
                .orElseThrow(
                    () -> new RuntimeException(
                        "Course not found."
                    )
                );
    }

}
