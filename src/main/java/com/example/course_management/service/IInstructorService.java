package com.example.course_management.service;

import java.util.List;

import com.example.course_management.model.Instructor;

public interface IInstructorService {
    
    List<Instructor> getAllInstructor();

    Instructor getInstructorById(Long id);

    Instructor createInstructor(Instructor instructor);

    Instructor updateInstructor(Long id, Instructor instructor);

    Instructor deleteInstructorById(Long id);

}
