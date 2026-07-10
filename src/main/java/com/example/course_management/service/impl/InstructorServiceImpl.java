package com.example.course_management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.course_management.dto.instructor.InstructorDetail;
import com.example.course_management.dto.instructor.InstructorRequest;
import com.example.course_management.dto.instructor.InstructorResponse;
import com.example.course_management.entity.Instructor;
import com.example.course_management.repository.jpa.InstructorRepository;
import com.example.course_management.service.IInstructorDetailService;
import com.example.course_management.service.IInstructorService;


@Service
public class InstructorServiceImpl implements IInstructorService{

    private final InstructorRepository instructorRepository;
    private final IInstructorDetailService instructorDetailService;

    @Autowired
    public InstructorServiceImpl(
        InstructorRepository instructorRepository,
        IInstructorDetailService instructorDetailService
    ) {

        this.instructorRepository = instructorRepository;

        this.instructorDetailService = instructorDetailService;

    }

    @Override
    public List<InstructorResponse> getAllInstructor() {
        return instructorRepository.findAll().stream()
                    .map(
                        instructor -> InstructorResponse.builder()
                                        .id(instructor.getId())
                                        .name(instructor.getName())
                                        .email(instructor.getEmail())
                                        .build()
                    ).toList();
    }

    @Override
    public InstructorResponse getInstructorById(Long id) {

        Instructor instructor = instructorRepository.findById(id)
                .orElseThrow(
                    () -> new RuntimeException(
                        "Instructor not found."
                    )
                );

        return InstructorResponse.builder()
                    .id(instructor.getId())
                    .name(instructor.getName())
                    .email(instructor.getEmail())
                    .build();
    }

    @Override
    public InstructorResponse createInstructor(InstructorRequest instructorRequest) {

        Instructor instructor = Instructor.builder()
                                .id(null)
                                .name(instructorRequest.getName())
                                .email(instructorRequest.getEmail())
                                .build();

        instructor = instructorRepository.save(instructor);

        return InstructorResponse.builder()
                    .id(instructor.getId())
                    .name(instructor.getName())
                    .email(instructor.getEmail())
                    .build();

    }

    @Override
    public InstructorResponse updateInstructor(Long id, InstructorRequest instructorRequest) {

        try {
            getInstructorById(id);
        } catch (RuntimeException e) {
            throw new RuntimeException(
                "Instructor not found for update."
            );
        }

        Instructor instructor = Instructor.builder()
                                .id(id)
                                .name(instructorRequest.getName())
                                .email(instructorRequest.getEmail())
                                .build();

        instructor = instructorRepository.save(instructor);

        return InstructorResponse.builder()
                    .id(instructor.getId())
                    .name(instructor.getName())
                    .email(instructor.getEmail())
                    .build();
    }

    @Override
    public InstructorResponse deleteInstructorById(Long id) {

        try {
            InstructorResponse exist = getInstructorById(id);
            instructorRepository.deleteById(id);
            return exist;
        } catch (RuntimeException e) {
            throw new RuntimeException(
                "Instructor not found for delete."
            );
        }

    }

    @Override
    public List<InstructorDetail> getAllInstructorDetail() {
        return instructorRepository.findAll().stream()
                .map(
                    instructor -> InstructorDetail
                        .builder()
                        .id(instructor.getId())
                        .name(instructor.getName())
                        .email(instructor.getEmail())
                        .courseSummaries(
                            instructorDetailService
                                .getSummaryOfAllActivateCoursesAndHaveAtLeastOneEnrollmentByInstructorId(
                                    instructor.getId()
                                )
                        ).build()
                ).toList();
    }

}
