package com.coursemy_backend.coursemy.controllers;

import com.coursemy_backend.coursemy.dto.TeacherDTO;
import com.coursemy_backend.coursemy.entities.Teacher;
import com.coursemy_backend.coursemy.service.TeacherService;
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
    public List<TeacherDTO> getAll(){
        return teacherService.getAllTeachers();
    }

    @GetMapping("/teachers/{id}")
    public TeacherDTO getById(@PathVariable long id){
        return teacherService.getById(id);
    }

    @PostMapping("/teachers")
    public Teacher createTeacher(@Valid @RequestBody Teacher teacher){
        return teacherService.createTeacher(teacher);
    }
}
