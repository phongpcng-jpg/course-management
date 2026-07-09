package com.example.course_management.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.course_management.repository.CourseRepository;
import com.example.course_management.service.ICourseService;


@Service
public class CourseServiceImpl implements ICourseService{

    private final CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

}
