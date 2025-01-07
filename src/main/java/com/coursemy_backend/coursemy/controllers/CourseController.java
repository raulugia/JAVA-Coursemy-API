package com.coursemy_backend.coursemy.controllers;

import com.coursemy_backend.coursemy.entities.Course;
import com.coursemy_backend.coursemy.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CourseController {
    private CourseService courseService;

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
    Course createCourse(@Valid @RequestBody Course course){
        return courseService.createCourse(course);
    }

    @PutMapping("courses/{id}")
    Course updateCourse(@Valid @PathVariable long id ,@RequestBody Course course){
        return courseService.updateCourse(id, course);
    }

    @DeleteMapping("/courses")
    String deleteCourse(@RequestBody long id){
        return courseService.removeById(id);
    }
}
