package com.example.course_management.service.legacy.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.example.course_management.dto.instructor.InstructorDetail;
import com.example.course_management.model.Instructor;
import com.example.course_management.repository.InstructorRepository;
import com.example.course_management.service.legacy.IInstructorDetailService;
import com.example.course_management.service.legacy.IInstructorService;


// @Repository
// @Profile("old")
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
    public List<Instructor> getAllInstructor() {
        return instructorRepository.findAll();
    }

    @Override
    public Instructor getInstructorById(Long id) {
        return instructorRepository.findById(id)
                .orElseThrow(
                    () -> new RuntimeException(
                        "Instructor not found."
                    )
                );
    }

    @Override
    public Instructor createInstructor(Instructor instructor) {
        return instructorRepository.create(instructor);
    }

    @Override
    public Instructor updateInstructor(Long id, Instructor instructor) {
        return instructorRepository.update(id, instructor)
                .orElseThrow(
                    () -> new RuntimeException(
                        "Instructor not found."
                    )
                );
    }

    @Override
    public Instructor deleteInstructorById(Long id) {
        return instructorRepository.deleteById(id)
                .orElseThrow(
                    () -> new RuntimeException(
                        "Instructor not found."
                    )
                );
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
