package com.coursemy_backend.coursemy.service;

import com.coursemy_backend.coursemy.dto.CourseDTO;
import com.coursemy_backend.coursemy.dto.TeacherDTO;
import com.coursemy_backend.coursemy.entities.Course;
import com.coursemy_backend.coursemy.entities.Teacher;
import com.coursemy_backend.coursemy.exception.EntityNotFound;
import com.coursemy_backend.coursemy.exception.NotAuthorized;
import com.coursemy_backend.coursemy.repository.CourseRepository;
import com.coursemy_backend.coursemy.repository.TeacherRepository;
import jakarta.transaction.Transactional;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImp implements CourseService{
    private CourseRepository courseRepository;
    private TeacherRepository teacherRepository;
    
    @Autowired
    public CourseServiceImp(CourseRepository courseRepository, TeacherRepository teacherRepository){
        this.courseRepository = courseRepository;
        this.teacherRepository = teacherRepository;
    }

    @Transactional
    @Override
    public List<CourseDTO> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream()
                .map(course -> new CourseDTO(course.getName(), course.getDescription(), course.getImageUrl(), course.getTeacher().getId(), new TeacherDTO(course.getTeacher().getId(), course.getTeacher().getFirstName(), course.getTeacher().getLastName())))
                .collect(Collectors.toList());
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
    public Course createCourse(CourseDTO course) {
        Optional<Teacher> dbTeacher = teacherRepository.findById(course.getId());
        if(dbTeacher.isPresent()){
            Teacher teacher = dbTeacher.get();
            Course newCourse = new Course();

            newCourse.setName(course.getName());
            newCourse.setDescription(course.getDescription());
            newCourse.setImageUrl(course.getImageUrl());
            newCourse.setTeacher(teacher);

            return courseRepository.save(newCourse);
        }

        throw new EntityNotFound("Teacher not found");
    }

    @Transactional
    @Override
    public Course updateCourse(long id, CourseDTO course) {
        Optional<Course> existingCourse = courseRepository.findById(id);

        if(existingCourse.isPresent()){
            Course dbCourse = existingCourse.get();
            if(dbCourse.getId() != course.getId()){
                throw new NotAuthorized("You cannot modify this course");
            }
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

        throw new EntityNotFound("Course not found");
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
