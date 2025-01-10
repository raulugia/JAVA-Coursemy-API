package com.coursemy_backend.coursemy.service;

import com.coursemy_backend.coursemy.dto.StudentDTO;
import com.coursemy_backend.coursemy.entities.Student;

import java.util.List;

public interface StudentService {
    List<StudentDTO> getAllStudents();

    StudentDTO getById(long id);

    Student createStudent(Student student);

    String enrollCourse(long studentId, long courseId);

    String removeEnrollment(long courseId, long studentId);

    Student updateStudent(long id, Student student);

    String removeById(long id);
}
