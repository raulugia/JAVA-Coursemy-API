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
                .map(course -> new CourseDTO(course.getId(), course.getName(), course.getDescription(), course.getImageUrl(), new TeacherDTO(course.getTeacher().getId(), course.getTeacher().getFirstName(), course.getTeacher().getLastName())))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public CourseDTO getById(long id) {
        Optional<Course> existingCourse = courseRepository.findById(id);

        if(existingCourse.isPresent()){
            Course dbCourse = existingCourse.get();
            return new CourseDTO(dbCourse.getId(), dbCourse.getName(), dbCourse.getDescription(), dbCourse.getImageUrl(),new TeacherDTO(dbCourse.getTeacher().getId(),dbCourse.getTeacher().getFirstName(), dbCourse.getTeacher().getLastName()));
        }

        return null;
    }

    @Transactional
    @Override
    public CourseDTO createCourse(CourseDTO course) {
        Optional<Teacher> dbTeacher = teacherRepository.findById(course.getTeacherId());
        if(dbTeacher.isPresent()){
            Teacher teacher = dbTeacher.get();
            Course newCourse = new Course();

            newCourse.setName(course.getName());
            newCourse.setDescription(course.getDescription());
            newCourse.setImageUrl(course.getImageUrl());
            newCourse.setTeacher(teacher);

            Course dbCourse = courseRepository.save(newCourse);
            return new CourseDTO(dbCourse.getId(),dbCourse.getName(), dbCourse.getDescription(), dbCourse.getImageUrl(), new TeacherDTO(teacher.getId(), teacher.getFirstName(), teacher.getLastName()));
        }

        throw new EntityNotFound("Teacher not found");
    }

    @Transactional
    @Override
    public CourseDTO updateCourse(long id, CourseDTO course) {
        Optional<Course> existingCourse = courseRepository.findById(id);
        if(existingCourse.isPresent()){
            Course dbCourse = existingCourse.get();
            if(dbCourse.getTeacher().getId() != course.getTeacherId()){
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

            Course updatedCourse = courseRepository.save(dbCourse);

            return new CourseDTO(updatedCourse.getId(), updatedCourse.getName(), updatedCourse.getDescription(), updatedCourse.getImageUrl(), new TeacherDTO(updatedCourse.getTeacher().getId(),updatedCourse.getTeacher().getFirstName(), updatedCourse.getTeacher().getLastName()));
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
