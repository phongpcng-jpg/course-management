package com.example.course_management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.course_management.dto.course.CourseRequest;
import com.example.course_management.dto.course.CourseResponse;
import com.example.course_management.dto.instructor.InstructorResponse;
import com.example.course_management.entity.Course;
import com.example.course_management.entity.Instructor;
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
    public List<CourseResponse> getAllCourses() {
        return courseRepository.findAll().stream()
                    .map(
                        course -> CourseResponse.builder()
                                    .id(course.getId())
                                    .title(course.getTitle())
                                    .status(course.getStatus())
                                    .instructorId(course.getInstructor().getId())
                                    .build()
                    ).toList();
    }

    @Override
    public CourseResponse getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(
                    () -> new RuntimeException(
                        "Course not found."
                    )
                );

        return CourseResponse.builder()
                    .id(course.getId())
                    .title(course.getTitle())
                    .status(course.getStatus())
                    .instructorId(course.getInstructor().getId())
                    .build();
    }

    @Override
    public CourseResponse createCourse(CourseRequest courseRequest) {

        Course course = Course.builder()
                    .id(null)
                    .title(courseRequest.getTitle())
                    .status(courseRequest.getStatus())
                    .build();

        try {

            InstructorResponse instructorResponse = instructorService.getInstructorById(
                courseRequest.getInstructorId()
            );

            course.setInstructor(
                Instructor.builder()
                    .id(instructorResponse.getId())
                    .name(instructorResponse.getName())
                    .email(instructorResponse.getEmail())
                    .build()
            );

        } catch (RuntimeException e) {
            throw new RuntimeException(
                "Instructor of course not found."
            );
        }

        course =  courseRepository.save(course);

        return CourseResponse.builder()
                    .id(course.getId())
                    .title(course.getTitle())
                    .status(course.getStatus())
                    .instructorId(course.getInstructor().getId())
                    .build();
        
    }

    @Override
    public CourseResponse updateCourse(Long id, CourseRequest courseRequest) {

        try {
            getCourseById(id);
        } catch (RuntimeException e) {
            throw new RuntimeException(
                "Course not found for update."
            );
        }

        Course course = Course.builder()
                    .id(id)
                    .title(courseRequest.getTitle())
                    .status(courseRequest.getStatus())
                    .build();
        
        try {

            InstructorResponse instructorResponse = instructorService.getInstructorById(
                courseRequest.getInstructorId()
            );

            course.setInstructor(
                Instructor.builder()
                    .id(instructorResponse.getId())
                    .name(instructorResponse.getName())
                    .email(instructorResponse.getEmail())
                    .build()
            );

        } catch (RuntimeException e) {
            throw new RuntimeException(
                "Instructor of course not found."
            );
        }

        course =  courseRepository.save(course);

        return CourseResponse.builder()
                    .id(course.getId())
                    .title(course.getTitle())
                    .status(course.getStatus())
                    .instructorId(course.getInstructor().getId())
                    .build();

    }

    @Override
    public CourseResponse deleteCourseById(Long id) {

        try {
            CourseResponse exist = getCourseById(id);
            courseRepository.deleteById(id);
            return exist;
        } catch (RuntimeException e) {
            throw new RuntimeException(
                "Course not found for delete."
            );
        }

    }

}
