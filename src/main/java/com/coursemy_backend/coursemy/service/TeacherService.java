package com.coursemy_backend.coursemy.service;

import com.coursemy_backend.coursemy.dto.TeacherDTO;
import com.coursemy_backend.coursemy.entities.Teacher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeacherService {
    List<TeacherDTO> getAllTeachers();

    TeacherDTO getById(long id);

    TeacherDTO getTeacherCourses(long id);

    Teacher createTeacher(Teacher teacher);

    Teacher updateTeacher(long id, Teacher teacher);

    String removeById(long id);
}
