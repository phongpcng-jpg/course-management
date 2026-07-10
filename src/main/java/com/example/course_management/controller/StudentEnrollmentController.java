package com.example.course_management.controller;

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

import com.example.course_management.dto.student_enrollment.StudentEnrollmentRequest;
import com.example.course_management.dto.student_enrollment.StudentEnrollmentResponse;
import com.example.course_management.response.ApiResponse;
import com.example.course_management.service.IStudentEnrollmentService;

@RestController
@RequestMapping("api/student-enrollments")
public class StudentEnrollmentController {

    private final IStudentEnrollmentService studentEnrollmentService;

    @Autowired
    public StudentEnrollmentController(IStudentEnrollmentService studentEnrollmentService) {
        this.studentEnrollmentService = studentEnrollmentService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<StudentEnrollmentResponse>>> getAllStudentEnrollment() {

        List<StudentEnrollmentResponse> studentEnrollmentResponses =
                studentEnrollmentService.getAllStudentEnrollment();

        ApiResponse<List<StudentEnrollmentResponse>> response = new ApiResponse<>(
                true,
                "Get all student enrollments successfully.",
                studentEnrollmentResponses
        );

        return ResponseEntity.ok(response);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentEnrollmentResponse>> getStudentEnrollment(
            @PathVariable("id") Long id) {

        try {
            return ResponseEntity.ok(
                    new ApiResponse<>(
                            true,
                            "Fetched successfully!",
                            studentEnrollmentService.getStudentEnrollmentById(id)
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
    public ResponseEntity<ApiResponse<StudentEnrollmentResponse>> createStudentEnrollment(
            @RequestBody StudentEnrollmentRequest studentEnrollmentRequest) {

        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(
                            true,
                            "Student enrollment created successfully.",
                            studentEnrollmentService.createStudentEnrollment(studentEnrollmentRequest)
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
    public ResponseEntity<ApiResponse<StudentEnrollmentResponse>> updateStudentEnrollment(
            @PathVariable("id") Long id,
            @RequestBody StudentEnrollmentRequest studentEnrollmentRequest) {

        try {
            return ResponseEntity.ok(
                    new ApiResponse<>(
                            true,
                            "Student enrollment updated successfully.",
                            studentEnrollmentService.updateStudentEnrollment(id, studentEnrollmentRequest)
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
    public ResponseEntity<ApiResponse<StudentEnrollmentResponse>> deleteStudentEnrollment(
            @PathVariable("id") Long id) {

        try {
            return ResponseEntity.ok(
                    new ApiResponse<>(
                            true,
                            "Student enrollment deleted successfully.",
                            studentEnrollmentService.deleteStudentEnrollmentById(id)
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