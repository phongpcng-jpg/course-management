package com.example.course_management.service;

import java.util.List;

import com.example.course_management.dto.student.StudentRequest;
import com.example.course_management.dto.student.StudentResponse;

public interface IStudentService {

    List<StudentResponse> getAllStudents();

    StudentResponse getStudentById(Long id);

    StudentResponse createStudent(StudentRequest studentRequest);

    StudentResponse updateStudent(Long id, StudentRequest studentRequest);

    StudentResponse deleteStudentById(Long id);

}
