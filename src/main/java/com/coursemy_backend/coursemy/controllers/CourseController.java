package com.coursemy_backend.coursemy.controllers;

import com.coursemy_backend.coursemy.dto.CourseDTO;
import com.coursemy_backend.coursemy.entities.Course;
import com.coursemy_backend.coursemy.entities.Teacher;
import com.coursemy_backend.coursemy.service.CourseService;
import com.coursemy_backend.coursemy.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CourseController {
    private CourseService courseService;
    private TeacherService teacherService;

    @Autowired
    CourseController(CourseService courseService){
        this.courseService = courseService;
    }

    @GetMapping("/courses")
    public List<Course> findAll(){
        return courseService.getAllCourses();
    }

    @GetMapping("/courses/{id}")
    public Course findById(@PathVariable long id){
        return courseService.getById(id);
    }

    @PostMapping("/courses")
    Course createCourse(@Valid @RequestBody CourseDTO courseRequest){
        return courseService.createCourse(courseRequest);
    }

    @PutMapping("courses/{id}")
    Course updateCourse(@Valid long id, CourseDTO course){
        return courseService.updateCourse(id, course);
    }

    @DeleteMapping("/courses")
    String deleteCourse(@RequestBody long id){
        return courseService.removeById(id);
    }
}
