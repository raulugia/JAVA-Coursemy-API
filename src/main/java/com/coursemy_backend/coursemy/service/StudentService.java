package com.coursemy_backend.coursemy.service;

import com.coursemy_backend.coursemy.entities.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllTeachers();

    Student getById(int id);

    Student createStudent(Student teacher);

    Student updateStudent(int id, Student teacher);

    String removeById(int id);
}
