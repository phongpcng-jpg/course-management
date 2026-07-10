package com.example.course_management.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.example.course_management.model.Instructor;


// @Repository
// @Profile("old")
public class InstructorRepository {

    private final List<Instructor> instructors = new ArrayList<>();
    private Long nextId;

    public InstructorRepository() {
        nextId = 1L;
        initData();
    }
    
    private void initData() {
        
        nextId = Optional.ofNullable(nextId).orElse(1L);

        instructors.add(
            Instructor.builder()
                    .id(1L)
                    .name("John Smith")
                    .email("john@gmail.com")
                    .build()
        );
        
        instructors.add(
            Instructor.builder()
                    .id(2L)
                    .name("Alice Brown")
                    .email("alice@gmail.com")
                    .build()
        );

        nextId += 2L;

    }

    public List<Instructor> findAll() {
        return instructors;
    }

    public Optional<Instructor> findById(Long id) {
        return instructors.stream()
                .filter(instructor -> instructor.getId().equals(id))
                .findFirst();
    }

    public Instructor create(Instructor instructor) {

        instructor.setId(nextId);
        nextId += 1L;
        instructors.add(instructor);

        return instructor;

    }

    public Optional<Instructor> update(Long id, Instructor instructor) {

        Optional<Instructor> existOpt = findById(id);

        existOpt.ifPresent(exist -> {
            exist.setName(instructor.getName());
            exist.setEmail(instructor.getEmail());
        });

        return existOpt;
        
    }

    public Optional<Instructor> deleteById(Long id) {

        Optional<Instructor> existOpt = findById(id);

        existOpt.ifPresent(instructors::remove);

        return existOpt;

    }

}
