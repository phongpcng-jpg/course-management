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

import com.example.course_management.dto.enrollment.EnrollCourseRequest;
import com.example.course_management.dto.enrollment.EnrollmentDetail;
import com.example.course_management.model.Enrollment;
import com.example.course_management.response.ApiResponse;
import com.example.course_management.service.legacy.IEnrollmentService;


// @RestController
// @RequestMapping("api/enrollments")
public class EnrollmentController {

    private final IEnrollmentService enrollmentService;

    @Autowired
    public EnrollmentController(IEnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Enrollment>>> getAllEnrollment() {

        List<Enrollment> enrollments = enrollmentService.getAllEnrollment();

        ApiResponse<List<Enrollment>> response = new ApiResponse<>(
                true,
                "Get all enrollments successfully.",
                enrollments
        );

        return ResponseEntity.ok(response);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Enrollment>> getEnrollment(
            @PathVariable("id") Long id) {

        try {
            return ResponseEntity.ok(
                    new ApiResponse<>(
                            true,
                            "Fetched successfully!",
                            enrollmentService.getEnrollmentById(id)
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

    // @PostMapping
    // public ResponseEntity<ApiResponse<Enrollment>> createEnrollmentOld(
    //         @RequestBody Enrollment enrollment) {

    //     try {
    //         return ResponseEntity
    //                 .status(HttpStatus.CREATED)
    //                 .body(new ApiResponse<>(
    //                         true,
    //                         "Enrollment created successfully.",
    //                         enrollmentService.createEnrollmentOld(enrollment)
    //                 ));
    //     } catch (RuntimeException e) {
    //         return ResponseEntity
    //                 .badRequest()
    //                 .body(new ApiResponse<>(
    //                         false,
    //                         e.getMessage(),
    //                         null
    //                 ));
    //     }

    // }

    @PostMapping("/enroll-course")
    public ResponseEntity<ApiResponse<EnrollmentDetail>> createEnrollment(
            @RequestBody EnrollCourseRequest enrollmentRequest) {

        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(
                            true,
                            "Enrollment created successfully.",
                            enrollmentService.createEnrollment(enrollmentRequest)
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

    // @PutMapping("/{id}")
    // public ResponseEntity<ApiResponse<Enrollment>> updateEnrollmentOld(
    //         @PathVariable("id") Long id,
    //         @RequestBody Enrollment enrollment) {

    //     try {
    //         return ResponseEntity.ok(
    //                 new ApiResponse<>(
    //                         true,
    //                         "Enrollment updated successfully.",
    //                         enrollmentService.updateEnrollmentOld(id, enrollment)
    //                 )
    //         );
    //     } catch (RuntimeException e) {
    //         return ResponseEntity
    //                 .status(HttpStatus.NOT_FOUND)
    //                 .body(new ApiResponse<>(
    //                         false,
    //                         e.getMessage(),
    //                         null
    //                 ));
    //     }

    // }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EnrollmentDetail>> updateEnrollment(
            @PathVariable("id") Long id,
            @RequestBody EnrollCourseRequest enrollment) {

        try {
            return ResponseEntity.ok(
                    new ApiResponse<>(
                            true,
                            "Enrollment updated successfully.",
                            enrollmentService.updateEnrollment(id, enrollment)
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
    public ResponseEntity<ApiResponse<Enrollment>> deleteEnrollment(
            @PathVariable("id") Long id) {

        try {


            return ResponseEntity.ok(
                    new ApiResponse<>(
                            true,
                            "Enrollment deleted successfully.",
                            enrollmentService.deleteEnrollmentById(id)
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
