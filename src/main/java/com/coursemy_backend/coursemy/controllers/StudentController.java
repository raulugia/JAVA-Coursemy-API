package com.coursemy_backend.coursemy.controllers;

import com.coursemy_backend.coursemy.dto.CourseDTO;
import com.coursemy_backend.coursemy.dto.StudentDTO;
import com.coursemy_backend.coursemy.entities.Student;
import com.coursemy_backend.coursemy.service.StudentService;
import com.coursemy_backend.coursemy.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
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
    @JsonView(Views.Basic.class)
    public List<StudentDTO> findAll(){
        return studentService.getAllStudents();
    }

    @GetMapping("/students/{id}")
    public StudentDTO findById(@PathVariable long id){
        return studentService.getById(id);
    }

    @GetMapping("/students/{id}/courses")
    @JsonView(Views.Basic.class)
    public List<CourseDTO> getStudentCourses(@PathVariable long id){
        return studentService.getStudentCourses(id);
    }

    @PostMapping("/students")
    @JsonView(Views.Detailed.class)
    public StudentDTO createStudent(@Valid @RequestBody Student student){
        return studentService.createStudent(student);
    }

    @PostMapping("/courses/{courseId}/enroll/{studentId}")
    public String enrollCourse(@PathVariable long studentId, @PathVariable long courseId){
        return studentService.enrollCourse(studentId, courseId);
    }

    @PutMapping("/students/{id}")
    @JsonView(Views.Detailed.class)
    public StudentDTO updateStudent(@Valid @PathVariable long id, @RequestBody Student student){
        return studentService.updateStudent(id, student);
    }

    @DeleteMapping("/students/{id}")
    public String deleteStudent(@PathVariable long id){
        return studentService.removeById(id);
    }

    @DeleteMapping("/courses/{courseId}/enroll/{studentId}")
    public String deleteEnrollment(@PathVariable long courseId, @PathVariable long studentId){
        return studentService.removeEnrollment(courseId, studentId);
    }

}
