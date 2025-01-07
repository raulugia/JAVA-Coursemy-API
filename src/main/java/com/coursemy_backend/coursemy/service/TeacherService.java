package com.coursemy_backend.coursemy.service;

import com.coursemy_backend.coursemy.entities.Teacher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeacherService {
    List<Teacher> getAllTeachers();

    Teacher getById(long id);

    Teacher createTeacher(Teacher teacher);

    Teacher updateTeacher(long id, Teacher teacher);

    String removeById(long id);
}
