package com.example.course_management.controller.legacy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.course_management.model.Course;
import com.example.course_management.response.ApiResponse;
import com.example.course_management.service.legacy.ICourseService;


// @RestController
// @RequestMapping("api/courses")
public class CourseController {

    private final ICourseService courseService;

    @Autowired
    public CourseController(ICourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Course>>> getAllCourse() {

        List<Course> courses = courseService.getAllCourses();

        ApiResponse<List<Course>> response = new ApiResponse<>(
                true,
                "Get all courses successfully.",
                courses
        );

        return ResponseEntity.ok(response);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> getCourse(
            @PathVariable("id") Long id) {

        try {

            return ResponseEntity.ok(
                    new ApiResponse<>(
                            true,
                            "Fetched successfully!",
                            courseService.getCourseById(id)
                    )
            );
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(
                            false,
                            e.getMessage(),
                            null
                    ));
        }

    }

    @PostMapping
    public ResponseEntity<ApiResponse<Course>> createCourse(
            @RequestBody Course course) {

        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(
                            true,
                            "Course created successfully.",
                            courseService.createCourse(course)
                    ));
        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(new ApiResponse<>(
                            false,
                            e.getMessage(),
                            null
                    ));
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> updateCourse(
            @PathVariable("id") Long id,
            @RequestBody Course course) {

        try {
            return ResponseEntity.ok(
                    new ApiResponse<>(
                            true,
                            "Course updated successfully.",
                            courseService.updateCourse(id, course)
                    )
            );
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(
                            false,
                            e.getMessage(),
                            null
                    ));
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> deleteCourse(
            @PathVariable("id") Long id) {

        try {
            return ResponseEntity.ok(
                    new ApiResponse<>(
                            true,
                            "Course deleted successfully.",
                            courseService.deleteCourseById(id)
                    )
            );
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(
                            false,
                            e.getMessage(),
                            null
                    ));
        }

    }

}
