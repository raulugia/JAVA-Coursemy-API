package com.coursemy_backend.coursemy.controllers;

import com.coursemy_backend.coursemy.dto.CourseDTO;
import com.coursemy_backend.coursemy.service.CourseService;
import com.coursemy_backend.coursemy.service.TeacherService;
import com.coursemy_backend.coursemy.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @JsonView(Views.Basic.class)
    public List<CourseDTO> findAll(){
        return courseService.getAllCourses();
    }

    @GetMapping("/courses/{id}")
    @JsonView(Views.Basic.class)
    public CourseDTO findById(@PathVariable long id){
        return courseService.getById(id);
    }

    @PostMapping("/courses")
    @JsonView(Views.Basic.class)
    public CourseDTO createCourse(@Valid @RequestBody CourseDTO courseRequest){
        return courseService.createCourse(courseRequest);
    }

    @PutMapping("/courses/{id}")
    @JsonView(Views.Basic.class)
    public CourseDTO updateCourse(@Valid @PathVariable long id, @RequestBody CourseDTO course){
        return courseService.updateCourse(id, course);
    }

    @DeleteMapping("/courses/{id}")
    public String deleteCourse(@PathVariable long id){
        return courseService.removeById(id);
    }
}
