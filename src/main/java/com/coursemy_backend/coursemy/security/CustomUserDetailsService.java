package com.coursemy_backend.coursemy.security;

import com.coursemy_backend.coursemy.entities.Teacher;
import com.coursemy_backend.coursemy.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private TeacherRepository teacherRepository;

    @Autowired
    CustomUserDetailsService(TeacherRepository teacherRepository){
        this.teacherRepository = teacherRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Teacher teacher = teacherRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new CustomUserDetails(teacher.getEmail(), teacher.getPassword());
    }
}
