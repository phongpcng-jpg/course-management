package com.example.course_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.course_management.dto.course.CourseRequest;
import com.example.course_management.dto.course.CourseResponse;
import com.example.course_management.response.ApiResponse;
import com.example.course_management.response.PageResponse;
import com.example.course_management.service.ICourseService;


@RestController
@RequestMapping("api/courses")
public class CourseController {

    private final ICourseService courseService;

    @Autowired
    public CourseController(ICourseService courseService) {
        this.courseService = courseService;
    }

//     @GetMapping
//     public ResponseEntity<ApiResponse<List<CourseResponse>>> getAllCourse() {

//         List<CourseResponse> courseResponses = courseService.getAllCourses();

//         ApiResponse<List<CourseResponse>> response = new ApiResponse<>(
//                 true,
//                 "Get all courses successfully.",
//                 courseResponses
//         );

//         return ResponseEntity.ok(response);

//     }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<CourseResponse>>> getPagedCourses(

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "10")
            int size,

            @RequestParam(required = false)
            String sortBy,

            @RequestParam(defaultValue = "DESC")
            Sort.Direction direction
    ) {

        return ResponseEntity.ok(
            ApiResponse.success(
                courseService.getPagedCourses(
                        page,
                        size,
                        sortBy,
                        direction
                )
            )
        );

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CourseResponse>> getCourse(
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
    public ResponseEntity<ApiResponse<CourseResponse>> createCourse(
            @RequestBody CourseRequest courseRequest) {

        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(
                            true,
                            "Course created successfully.",
                            courseService.createCourse(courseRequest)
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
    public ResponseEntity<ApiResponse<CourseResponse>> updateCourse(
            @PathVariable("id") Long id,
            @RequestBody CourseRequest courseRequest) {

        try {
            return ResponseEntity.ok(
                    new ApiResponse<>(
                            true,
                            "Course updated successfully.",
                            courseService.updateCourse(id, courseRequest)
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
    public ResponseEntity<ApiResponse<CourseResponse>> deleteCourse(
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
