package com.coursemy_backend.coursemy.service;

import com.coursemy_backend.coursemy.dto.StudentDTO;
import com.coursemy_backend.coursemy.entities.Student;
import com.coursemy_backend.coursemy.exception.EntityNotFound;
import com.coursemy_backend.coursemy.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImp implements StudentService{
    private StudentRepository studentRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public StudentServiceImp(StudentRepository studentRepository, PasswordEncoder passwordEncoder){
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(student -> new StudentDTO(student.getId(), student.getFirstName(), student.getLastName()))
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO getById(long id) {
        Optional<Student> student = studentRepository.findById(id);

        if(student.isPresent()){
            Student dbStudent = student.get();
            return new StudentDTO(dbStudent.getId(), dbStudent.getFirstName(), dbStudent.getLastName());
        }
        throw  new EntityNotFound("Student not found");
    }

    @Transactional
    @Override
    public Student createStudent(Student student) {
        if(!validatePassword(student.getPassword())){
            throw new IllegalArgumentException("Password must be 6-20 characters long, contain at least one uppercase letter, and at least one lowercase letter.");
        }

        String password = passwordEncoder.encode(student.getPassword());
        student.setPassword(password);

        return studentRepository.save(student);
    }

    public boolean validatePassword(String password){
        String regex = "^(?=.*[a-z])(?=.*[A-Z]).{6,20}$";
        return password != null && password.matches(regex);
    }

    @Transactional
    @Override
    public Student updateStudent(long id, Student student) {
        Optional<Student> existingStudent = studentRepository.findById(id);
        System.out.println("Student " + existingStudent.toString());
        if(existingStudent.isPresent()){
            Student dbStudent = existingStudent.get();
            if(student.getFirstName() != null){
                dbStudent.setFirstName(student.getFirstName());
            }
            if(student.getLastName() != null){
                dbStudent.setLastName(student.getLastName());
            }
            if(student.getEmail() != null){
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
