package com.coursemy_backend.coursemy.controllers;

import com.coursemy_backend.coursemy.dto.TeacherDTO;
import com.coursemy_backend.coursemy.entities.Teacher;
import com.coursemy_backend.coursemy.service.TeacherService;
import com.coursemy_backend.coursemy.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TeachersController {
    private TeacherService teacherService;

    @Autowired
    public TeachersController(TeacherService teacherService){
        this.teacherService = teacherService;
    }

    @GetMapping("/teachers")
    @JsonView(Views.Basic.class)
    public List<TeacherDTO> getAll(){
        return teacherService.getAllTeachers();
    }

    @GetMapping("/teachers/{id}")
    @JsonView(Views.Basic.class)
    public TeacherDTO getById(@PathVariable long id){
        return teacherService.getById(id);
    }

    @GetMapping("/teachers/{id}/courses")
    @JsonView(Views.Basic.class)
    public TeacherDTO getTeacherCourses(@PathVariable long id){
        TeacherDTO response = teacherService.getTeacherCourses(id);
        System.out.println(response);
        return response;
    }

    @PostMapping("/teachers")
    @JsonView(Views.Detailed.class)
    public TeacherDTO createTeacher(@Valid @RequestBody Teacher teacher){
        return teacherService.createTeacher(teacher);
    }

    @PutMapping("/teachers/{id}")
    @JsonView(Views.Detailed.class)
    public TeacherDTO updateTeacher(@Valid @PathVariable long id, @RequestBody Teacher teacher){
        return teacherService.updateTeacher(id, teacher);
    }
}
