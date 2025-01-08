package com.coursemy_backend.coursemy.controllers;

import com.coursemy_backend.coursemy.dto.StudentDTO;
import com.coursemy_backend.coursemy.entities.Student;
import com.coursemy_backend.coursemy.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {
    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<StudentDTO> findAll(){
        return studentService.getAllStudents();
    }

    @GetMapping("/students/{id}")
    public Student findById(@PathVariable long id){
        return studentService.getById(id);
    }

    @PostMapping("/students")
    public Student createStudent(@Valid @RequestBody Student student){
        return studentService.createStudent(student);
    }

    @PutMapping("/students/{id}")
    public Student updateStudent(@Valid @PathVariable long id, @RequestBody Student student){
        return studentService.updateStudent(id, student);
    }

    @DeleteMapping("/students/{id}")
    public String deleteStudent(@PathVariable long id){
        return studentService.removeById(id);
    }
}
