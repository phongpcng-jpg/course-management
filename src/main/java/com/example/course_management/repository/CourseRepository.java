package com.example.course_management.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.course_management.enums.CourseStatus;
import com.example.course_management.model.Course;


@Repository
public class CourseRepository {

    private final List<Course> courses = new ArrayList<>();
    private Long nextId;

    public CourseRepository() {
        nextId = 1L;
        initData();
    }
    
    private void initData() {
        
        nextId = Optional.ofNullable(nextId).orElse(1L);

        courses.add(
            Course.builder()
                    .id(1L)
                    .title("Spring Boot")
                    .status(CourseStatus.ACTIVE)
                    .instructorId(1L)
                    .build()
        );

        courses.add(
            Course.builder()
                    .id(2L)
                    .title("AngularJS")
                    .status(CourseStatus.ACTIVE)
                    .instructorId(2L)
                    .build()
        );

        nextId += 2L;

    }

    public List<Course> findAll() {
        return courses;
    }

    public Optional<Course> findById(Long id) {
        return courses.stream()
                .filter(course -> course.getId().equals(id))
                .findFirst();
    }

    public Course create(Course course) {

        course.setId(nextId);
        courses.add(course);
        nextId += 1L;

        return course;

    }

    public Optional<Course> update(Long id, Course course) {

        Optional<Course> existOpt = findById(id);

        existOpt.ifPresent(exist -> {
            exist.setTitle(course.getTitle());
            exist.setStatus(course.getStatus());
            exist.setInstructorId(course.getInstructorId());
        });

        return existOpt;
        
    }

    public Optional<Course> deleteById(Long id) {
    
        Optional<Course> existOpt = findById(id);

        existOpt.ifPresent(courses::remove);

        return existOpt;

    }

}
