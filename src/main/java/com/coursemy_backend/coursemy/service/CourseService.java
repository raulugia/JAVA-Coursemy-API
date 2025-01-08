package com.coursemy_backend.coursemy.service;

import com.coursemy_backend.coursemy.dto.CourseDTO;
import com.coursemy_backend.coursemy.entities.Course;

import java.util.List;

public interface CourseService {
    List<CourseDTO> getAllCourses();

    CourseDTO getById(long id);

    Course createCourse(CourseDTO course);

    CourseDTO updateCourse(long id, CourseDTO course );

    String removeById(long id);
}
