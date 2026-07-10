package com.example.course_management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.course_management.dto.course.CourseResponse;
import com.example.course_management.dto.student.StudentResponse;
import com.example.course_management.dto.student_enrollment.StudentEnrollmentRequest;
import com.example.course_management.dto.student_enrollment.StudentEnrollmentResponse;
import com.example.course_management.entity.StudentEnrollment;
import com.example.course_management.enums.CourseStatus;
import com.example.course_management.repository.jpa.CourseRepository;
import com.example.course_management.repository.jpa.StudentEnrollmentRepository;
import com.example.course_management.repository.jpa.StudentRepository;
import com.example.course_management.service.IInstructorService;
import com.example.course_management.service.IStudentEnrollmentService;


@Service
public class StudentEnrollmentServiceImpl implements IStudentEnrollmentService{

    private final StudentEnrollmentRepository studentEnrollmentRepository;
    private final CourseRepository courseRepository;
    private final IInstructorService instructorService;
    private final StudentRepository studentRepository;

    @Autowired
    public StudentEnrollmentServiceImpl(
        StudentEnrollmentRepository studentEnrollmentRepository,
        CourseRepository courseRepository,
        IInstructorService instructorService,
        StudentRepository studentRepository
    ) {

        this.studentEnrollmentRepository = studentEnrollmentRepository;

        this.courseRepository = courseRepository;

        this.instructorService = instructorService;

        this.studentRepository = studentRepository;

    }

    @Override
    public List<StudentEnrollmentResponse> getAllStudentEnrollment() {
        return studentEnrollmentRepository.findAll().stream()
                    .map(
                        studentEnrollment -> StudentEnrollmentResponse.builder()
                                    .id(studentEnrollment.getId())
                                    .student(
                                        StudentResponse.builder()
                                            .id(studentEnrollment.getStudent().getId())
                                            .name(studentEnrollment.getStudent().getName())
                                            .email(studentEnrollment.getStudent().getEmail())
                                            .build()
                                    ).course(
                                        CourseResponse.builder()
                                            .id(studentEnrollment.getCourse().getId())
                                            .title(studentEnrollment.getCourse().getTitle())
                                            .status(studentEnrollment.getCourse().getStatus())
                                            .instructorId(studentEnrollment.getCourse().getInstructor().getId())
                                            .build()
                                    ).build()
                    ).toList();
    }

    @Override
    public StudentEnrollmentResponse getStudentEnrollmentById(Long id) {
        
        StudentEnrollment studentEnrollment = studentEnrollmentRepository.findById(id)
                .orElseThrow(
                    () -> new RuntimeException(
                        "Student Enrollment not found."
                    )
                );

        return StudentEnrollmentResponse.builder()
                    .id(studentEnrollment.getId())
                    .student(
                        StudentResponse.builder()
                            .id(studentEnrollment.getStudent().getId())
                            .name(studentEnrollment.getStudent().getName())
                            .email(studentEnrollment.getStudent().getEmail())
                            .build()
                    ).course(
                        CourseResponse.builder()
                            .id(studentEnrollment.getCourse().getId())
                            .title(studentEnrollment.getCourse().getTitle())
                            .status(studentEnrollment.getCourse().getStatus())
                            .instructorId(studentEnrollment.getCourse().getInstructor().getId())
                            .build()
                    ).build();

    }

    @Override
    public StudentEnrollmentResponse createStudentEnrollment(StudentEnrollmentRequest studentEnrollmentRequest) {

        StudentEnrollment studentEnrollment = StudentEnrollment.builder()
                        .id(null)
                        .student(
                            studentRepository.findById(
                                studentEnrollmentRequest.getStudentId()
                            ).orElseThrow(
                                () -> new RuntimeException(
                                    "Student of student enrollment not found."
                                )
                            )
                        ).course(
                            courseRepository.findById(
                                studentEnrollmentRequest.getCourseId()
                            ).orElseThrow(
                                () -> new RuntimeException(
                                    "Course of student enrollment not found."
                                )
                            )
                        )
                        .build();

        if (studentEnrollment.getCourse().getStatus() != CourseStatus.ACTIVE) {
            throw new RuntimeException(
                "Course of student enrollment is not active."
            );
        }
        
        try {
            instructorService.getInstructorById(
                studentEnrollment
                    .getCourse()
                    .getInstructor()
                    .getId()
            );
        } catch (RuntimeException e) {
            throw new RuntimeException(
                "Instructor of student enrollment's course not found."
            );
        }

        studentEnrollment = studentEnrollmentRepository.save(studentEnrollment);

        return StudentEnrollmentResponse.builder()
                    .id(studentEnrollment.getId())
                    .student(
                        StudentResponse.builder()
                            .id(studentEnrollment.getStudent().getId())
                            .name(studentEnrollment.getStudent().getName())
                            .email(studentEnrollment.getStudent().getEmail())
                            .build()
                    ).course(
                        CourseResponse.builder()
                            .id(studentEnrollment.getCourse().getId())
                            .title(studentEnrollment.getCourse().getTitle())
                            .status(studentEnrollment.getCourse().getStatus())
                            .instructorId(studentEnrollment.getCourse().getInstructor().getId())
                            .build()
                    ).build();

    }

    @Override
    public StudentEnrollmentResponse updateStudentEnrollment(Long id,
            StudentEnrollmentRequest studentEnrollmentRequest) {

        try {
            getStudentEnrollmentById(id);
        } catch (RuntimeException e) {
            throw new RuntimeException(
                "Student Enrollment not found for update."
            );
        }

        StudentEnrollment studentEnrollment = StudentEnrollment.builder()
                        .id(id)
                        .student(
                            studentRepository.findById(
                                studentEnrollmentRequest.getStudentId()
                            ).orElseThrow(
                                () -> new RuntimeException(
                                    "Student of student enrollment not found."
                                )
                            )
                        ).course(
                            courseRepository.findById(
                                studentEnrollmentRequest.getCourseId()
                            ).orElseThrow(
                                () -> new RuntimeException(
                                    "Course of student enrollment not found."
                                )
                            )
                        )
                        .build();

        if (studentEnrollment.getCourse().getStatus() != CourseStatus.ACTIVE) {
            throw new RuntimeException(
                "Course of student enrollment is not active."
            );
        }
        
        try {
            instructorService.getInstructorById(
                studentEnrollment
                    .getCourse()
                    .getInstructor()
                    .getId()
            );
        } catch (RuntimeException e) {
            throw new RuntimeException(
                "Instructor of student enrollment's course not found."
            );
        }

        studentEnrollment = studentEnrollmentRepository.save(studentEnrollment);

        return StudentEnrollmentResponse.builder()
                    .id(studentEnrollment.getId())
                    .student(
                        StudentResponse.builder()
                            .id(studentEnrollment.getStudent().getId())
                            .name(studentEnrollment.getStudent().getName())
                            .email(studentEnrollment.getStudent().getEmail())
                            .build()
                    ).course(
                        CourseResponse.builder()
                            .id(studentEnrollment.getCourse().getId())
                            .title(studentEnrollment.getCourse().getTitle())
                            .status(studentEnrollment.getCourse().getStatus())
                            .instructorId(studentEnrollment.getCourse().getInstructor().getId())
                            .build()
                    ).build();
                    
    }

    @Override
    public StudentEnrollmentResponse deleteStudentEnrollmentById(Long id) {
        try {
            StudentEnrollmentResponse exist = getStudentEnrollmentById(id);
            studentEnrollmentRepository.deleteById(id);
            return exist;
        } catch (RuntimeException e) {
            throw new RuntimeException(
                "Student Enrollment not found for delete."
            );
        }
    }

}
