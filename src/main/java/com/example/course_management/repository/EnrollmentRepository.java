package com.example.course_management.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.course_management.model.Enrollment;


@Repository
public class EnrollmentRepository {

    private final List<Enrollment> enrollments = new ArrayList<>();
    private Long nextId;

    public EnrollmentRepository() {
        nextId = 1L;
        initData();
    }
    
    private void initData() {
        
        nextId = Optional.ofNullable(nextId).orElse(1L);

        enrollments.add(
            Enrollment.builder()
                    .id(1L)
                    .studentName("David")
                    .courseId(1L)
                    .build()
        );

        enrollments.add(
            Enrollment.builder()
                    .id(2L)
                    .studentName("Emma")
                    .courseId(2L)
                    .build()
        );

        nextId += 2L;

    }

    public List<Enrollment> findAll() {
        return enrollments;
    }

    public Optional<Enrollment> findById(Long id) {
        return enrollments.stream()
                .filter(enrollment -> enrollment.getId().equals(id))
                .findFirst();
    }

    public Enrollment create(Enrollment enrollment) {

        enrollment.setId(nextId);
        nextId += 1L;
        enrollments.add(enrollment);

        return enrollment;

    }

    public Optional<Enrollment> update(Long id, Enrollment enrollment) {

        Optional<Enrollment> existOpt = findById(id);

        existOpt.ifPresent(exist -> {
            exist.setStudentName(enrollment.getStudentName());
            exist.setCourseId(enrollment.getCourseId());
        });

        return existOpt;

    }

    public Optional<Enrollment> deleteById(Long id) {

        Optional<Enrollment> existOpt = findById(id);

        existOpt.ifPresent(enrollments::remove);

        return existOpt;
        
    }

}
