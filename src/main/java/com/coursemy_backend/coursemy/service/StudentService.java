package com.coursemy_backend.coursemy.service;

import com.coursemy_backend.coursemy.dto.CourseDTO;
import com.coursemy_backend.coursemy.dto.StudentDTO;
import com.coursemy_backend.coursemy.entities.Student;

import java.util.List;

public interface StudentService {
    List<StudentDTO> getAllStudents();

    StudentDTO getById(long id);

    List<CourseDTO> getStudentCourses(long id);

    StudentDTO createStudent(Student student);

    String enrollCourse(long studentId, long courseId);

    String removeEnrollment(long courseId, long studentId);

    StudentDTO updateStudent(long id, Student student);

    String removeById(long id);
}
