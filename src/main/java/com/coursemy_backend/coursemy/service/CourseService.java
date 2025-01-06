package com.coursemy_backend.coursemy.service;

import com.coursemy_backend.coursemy.entities.Course;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourses();

    Course getById(long id);

    Course createCourse(Course Course);

    Course updateCourse(long id, Course Course);

    String removeById(long id);
}
