package com.example.course_management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.course_management.dto.student.StudentRequest;
import com.example.course_management.dto.student.StudentResponse;
import com.example.course_management.entity.Student;
import com.example.course_management.repository.jpa.StudentRepository;
import com.example.course_management.service.IStudentService;


@Service
public class StudentServiceImpl implements IStudentService{

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {

        this.studentRepository = studentRepository;

    }

    @Override
    public List<StudentResponse> getAllStudents() {
        return studentRepository.findAll().stream()
                    .map(
                        student -> StudentResponse.builder()
                                    .id(student.getId())
                                    .name(student.getName())
                                    .email(student.getEmail())
                                    .build()
                    ).toList();
    }

    @Override
    public StudentResponse getStudentById(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(
                    () -> new RuntimeException(
                        "Student not found."
                    )
                );

        return StudentResponse.builder()
                    .id(student.getId())
                    .name(student.getName())
                    .email(student.getEmail())
                    .build();

    }

    @Override
    public StudentResponse createStudent(StudentRequest studentRequest) {
        
        Student student = Student.builder()
                            .id(null)
                            .name(studentRequest.getName())
                            .email(studentRequest.getEmail())
                            .build();

        student = studentRepository.save(student);

        return StudentResponse.builder()
                    .id(student.getId())
                    .name(student.getName())
                    .email(student.getEmail())
                    .build();

    }

    @Override
    public StudentResponse updateStudent(Long id, StudentRequest studentRequest) {
        
        try {
            getStudentById(id);
        } catch (RuntimeException e) {
            throw new RuntimeException(
                "Student not found for update."
            );
        }
        
        Student student = Student.builder()
                            .id(id)
                            .name(studentRequest.getName())
                            .email(studentRequest.getEmail())
                            .build();

        student = studentRepository.save(student);

        return StudentResponse.builder()
                    .id(student.getId())
                    .name(student.getName())
                    .email(student.getEmail())
                    .build();

    }

    @Override
    public StudentResponse deleteStudentById(Long id) {
        
        try {
            StudentResponse exist = getStudentById(id);
            studentRepository.deleteById(id);
            return exist;
        } catch (RuntimeException e) {
            throw new RuntimeException(
                "Student not found for delete."
            );
        }

    }

}
