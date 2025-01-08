package com.coursemy_backend.coursemy.service;

import com.coursemy_backend.coursemy.dto.CourseDTO;
import com.coursemy_backend.coursemy.entities.Course;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourses();

    Course getById(long id);

    Course createCourse(CourseDTO Course);

    Course updateCourse(long id, Course Course);

    String removeById(long id);
}
