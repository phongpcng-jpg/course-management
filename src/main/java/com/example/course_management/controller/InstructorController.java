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

import com.example.course_management.dto.instructor.InstructorDetail;
import com.example.course_management.dto.instructor.InstructorRequest;
import com.example.course_management.dto.instructor.InstructorResponse;
import com.example.course_management.response.ApiResponse;
import com.example.course_management.service.IInstructorService;


@RestController
@RequestMapping("api/instructors")
public class InstructorController {

    private final IInstructorService instructorService;

    @Autowired
    public InstructorController(IInstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<InstructorResponse>>> getAllInstructor() {

        List<InstructorResponse> instructorResponses = instructorService.getAllInstructor();

        ApiResponse<List<InstructorResponse>> response = new ApiResponse<>(
                true,
                "Get all instructors successfully.",
                instructorResponses
        );

        return ResponseEntity.ok(response);

    }

    @GetMapping("/details")
    public ResponseEntity<ApiResponse<List<InstructorDetail>>> getAllInstructorDetail() {

        List<InstructorDetail> instructorDetails = instructorService.getAllInstructorDetail();

        ApiResponse<List<InstructorDetail>> response = new ApiResponse<>(
                true,
                "Get all instructor details successfully.",
                instructorDetails
        );

        return ResponseEntity.ok(response);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<InstructorResponse>> getInstructor(
            @PathVariable("id") Long id)  {

        try {
            return ResponseEntity.ok(
                    new ApiResponse<>(
                            true,
                            "Fetched successfully!",
                            instructorService.getInstructorById(id)
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
    public ResponseEntity<ApiResponse<InstructorResponse>> createInstructor(
            @RequestBody InstructorRequest instructorRequest) {

        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(
                            true,
                            "Instructor created successfully.",
                            instructorService.createInstructor(instructorRequest)
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
    public ResponseEntity<ApiResponse<InstructorResponse>> updateInstructor(
            @PathVariable("id") Long id,
            @RequestBody InstructorRequest instructorRequest) {

        try {
            return ResponseEntity.ok(
                    new ApiResponse<>(
                            true,
                            "Instructor updated successfully.",
                            instructorService.updateInstructor(id, instructorRequest)
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
    public ResponseEntity<ApiResponse<InstructorResponse>> deleteInstructor(
            @PathVariable("id") Long id) {

        try {
            return ResponseEntity.ok(
                    new ApiResponse<>(
                            true,
                            "Instructor deleted successfully.",
                            instructorService.deleteInstructorById(id)
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
