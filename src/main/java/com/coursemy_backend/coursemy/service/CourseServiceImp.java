package com.coursemy_backend.coursemy.service;

import com.coursemy_backend.coursemy.entities.Course;
import com.coursemy_backend.coursemy.entities.Course;
import com.coursemy_backend.coursemy.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class CourseServiceImp implements CourseService{
    private CourseRepository courseRepository;
    
    @Autowired
    public CourseServiceImp(CourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getById(long id) {
        Optional<Course> existingCourse = courseRepository.findById(id);

        if(existingCourse.isPresent()){
            Course dbCourse = existingCourse.get();
            return dbCourse;
        }
    }

    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(long id, Course course) {
        Optional<Course> existingCourse = courseRepository.findById(id);

        if(existingCourse.isPresent()){
            Course dbCourse = existingCourse.get();
            if(course.getName() != null){
                dbCourse.setName(course.getName());
            }else if(course.getDescription() != null){
                dbCourse.setDescription(course.getDescription());
            }else if(course.getImageUrl() != null){
                dbCourse.setImageUrl(course.getImageUrl());
            }

            return courseRepository.save(dbCourse);
        }
        return null;
    }

    @Override
    public String removeById(long id) {
        Optional<Course> existingCourse = courseRepository.findById(id);

        if(existingCourse.isPresent()){
            courseRepository.deleteById(id);
            return "Course was deleted successfully";
        }
        return "Course not found";
    }
}
