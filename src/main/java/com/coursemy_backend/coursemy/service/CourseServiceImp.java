package com.coursemy_backend.coursemy.service;

import com.coursemy_backend.coursemy.entities.Course;
import com.coursemy_backend.coursemy.entities.Teacher;
import com.coursemy_backend.coursemy.repository.CourseRepository;
import com.coursemy_backend.coursemy.repository.TeacherRepository;
import jakarta.transaction.Transactional;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImp implements CourseService{
    private CourseRepository courseRepository;
    private TeacherRepository teacherRepository;
    
    @Autowired
    public CourseServiceImp(CourseRepository courseRepository, TeacherRepository teacherRepository){
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
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

        return null;
    }

    @Override
    public Course createCourse(Course course) {
        Optional<Teacher> dbTeacher = teacherRepository.findById(course.getTeacher().getId());
        if(dbTeacher.isPresent()){
            Teacher teacher = dbTeacher.get();
            course.setTeacher(teacher);

            return courseRepository.save(course);
        }

        throw new IllegalArgumentException("Teacher not found");
    }

    @Transactional
    @Override
    public Course updateCourse(long id, Course course) {
        Optional<Course> existingCourse = courseRepository.findById(id);

        if(existingCourse.isPresent()){
            Course dbCourse = existingCourse.get();
            if(course.getName() != null){
                dbCourse.setName(course.getName());
            }
            if(course.getDescription() != null){
                dbCourse.setDescription(course.getDescription());
            }
            if(course.getImageUrl() != null){
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
