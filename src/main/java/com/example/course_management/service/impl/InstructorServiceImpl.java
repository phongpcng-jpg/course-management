package com.example.course_management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.course_management.dto.instructor.InstructorDetail;
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

        instructor.setId(null);

        return instructorRepository.save(instructor);

    }

    @Override
    public Instructor updateInstructor(Long id, Instructor instructor) {

        try {
            getInstructorById(id);
        } catch (RuntimeException e) {
            throw new RuntimeException(
                "Instructor not found for update."
            );
        }

        instructor.setId(id);

        return instructorRepository.save(instructor);
    }

    @Override
    public Instructor deleteInstructorById(Long id) {

        try {
            Instructor exist = getInstructorById(id);
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
