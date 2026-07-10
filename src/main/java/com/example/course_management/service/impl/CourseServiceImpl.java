package com.example.course_management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.course_management.entity.Course;
import com.example.course_management.repository.jpa.CourseRepository;
import com.example.course_management.service.ICourseService;
import com.example.course_management.service.IInstructorService;


@Service
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
                course.getInstructor().getId()
            );
        } catch (RuntimeException e) {
            throw new RuntimeException(
                "Instructor of course not found."
            );
        }

        course.setId(null);

        return courseRepository.save(course);
        
    }

    @Override
    public Course updateCourse(Long id, Course course) {
        
        try {
            instructorService.getInstructorById(
                course.getInstructor().getId()
            );
        } catch (RuntimeException e) {
            throw new RuntimeException(
                "Instructor of course not found."
            );
        }

        try {
            getCourseById(id);
        } catch (RuntimeException e) {
            throw new RuntimeException(
                "Course not found for update."
            );
        }

        course.setId(id);

        return courseRepository.save(course);

    }

    @Override
    public Course deleteCourseById(Long id) {

        try {
            Course exist = getCourseById(id);
            courseRepository.deleteById(id);
            return exist;
        } catch (RuntimeException e) {
            throw new RuntimeException(
                "Course not found for delete."
            );
        }

    }

}
