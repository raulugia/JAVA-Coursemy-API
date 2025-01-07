package com.coursemy_backend.coursemy.security;

import com.coursemy_backend.coursemy.entities.Student;
import com.coursemy_backend.coursemy.entities.Teacher;
import com.coursemy_backend.coursemy.repository.StudentRepository;
import com.coursemy_backend.coursemy.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private TeacherRepository teacherRepository;
    private StudentRepository studentRepository;

    @Autowired
    CustomUserDetailsService(TeacherRepository teacherRepository, StudentRepository studentRepository){
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Teacher> teacher = teacherRepository.findByEmail(email);
        if(teacher.isPresent()){
            Teacher dbTeacher = teacher.get();
            return new CustomUserDetails(dbTeacher.getEmail(), dbTeacher.getPassword());
        }

        Optional<Student> student = studentRepository.findByEmail(email);
        if(student.isPresent()){
            Student dbStudent = student.get();
            return new CustomUserDetails(dbStudent.getEmail(), dbStudent.getPassword());
        }

        throw new UsernameNotFoundException("User not found");
    }
}
