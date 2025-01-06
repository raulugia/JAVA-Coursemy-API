package com.coursemy_backend.coursemy.service;

import com.coursemy_backend.coursemy.entities.Teacher;

import java.util.List;

public interface TeacherService {
    List<Teacher> getAllTeachers();

    Teacher getById(long id);

    Teacher createTeacher(Teacher teacher);

    Teacher updateTeacher(long id, Teacher teacher);

    String removeById(long id);
}
