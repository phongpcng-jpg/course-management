package com.example.course_management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.course_management.model.Instructor;
import com.example.course_management.repository.InstructorRepository;
import com.example.course_management.service.IInstructorService;


@Service
public class InstructorServiceImpl implements IInstructorService{

    private final InstructorRepository instructorRepository;

    @Autowired
    public InstructorServiceImpl(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
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

}
