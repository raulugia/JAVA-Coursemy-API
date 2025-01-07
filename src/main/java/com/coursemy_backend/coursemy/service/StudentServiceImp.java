package com.coursemy_backend.coursemy.service;

import com.coursemy_backend.coursemy.entities.Student;
import com.coursemy_backend.coursemy.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImp implements StudentService{
    private StudentRepository studentRepository;

    @Autowired
    public StudentServiceImp(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getById(long id) {
        Optional<Student> student = studentRepository.findById(id);

        if(student.isPresent()){
            Student dbStudent = student.get();
            return dbStudent;
        }
        return null;
    }

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(long id, Student student) {
        Optional<Student> existingStudent = studentRepository.findById(id);

        if(existingStudent.isPresent()){
            Student dbStudent = existingStudent.get();
            if(student.getFirstName() != null){
                dbStudent.setFirstName(student.getFirstName());
            }else if(student.getLastName() != null){
                dbStudent.setLastName(student.getLastName());
            }else if(student.getEmail() != null){
                dbStudent.setEmail(student.getEmail());
            }

            return studentRepository.save(dbStudent);
        }
        return null;
    }

    @Override
    public String removeById(long id) {
        Optional<Student> existingStudent = studentRepository.findById(id);

        if(existingStudent.isPresent()){
            studentRepository.deleteById(id);
            return "Student deleted successfully";
        }
        return "Error";
    }
}
