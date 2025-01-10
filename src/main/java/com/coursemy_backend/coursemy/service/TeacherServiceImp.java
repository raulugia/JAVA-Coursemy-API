package com.coursemy_backend.coursemy.service;

import com.coursemy_backend.coursemy.dto.CourseForTeacherDTO;
import com.coursemy_backend.coursemy.dto.TeacherDTO;
import com.coursemy_backend.coursemy.entities.Course;
import com.coursemy_backend.coursemy.entities.Teacher;
import com.coursemy_backend.coursemy.exception.EntityNotFound;
import com.coursemy_backend.coursemy.repository.CourseRepository;
import com.coursemy_backend.coursemy.repository.TeacherRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImp implements TeacherService {
    private TeacherRepository teacherRepository;
    private PasswordEncoder passwordEncoder;
    private CourseRepository courseRepository;

    @Autowired
    public TeacherServiceImp(TeacherRepository teacherRepository, PasswordEncoder passwordEncoder, CourseRepository courseRepository){
        this.teacherRepository = teacherRepository;
        this.passwordEncoder = passwordEncoder;
        this.courseRepository = courseRepository;
    }

    @Transactional
    @Override
    public List<TeacherDTO> getAllTeachers(){
        return teacherRepository.findAll()
                .stream()
                .map(teacher -> new TeacherDTO(teacher.getId(), teacher.getFirstName(), teacher.getLastName()))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public TeacherDTO getById(long id){
        Optional<Teacher> teacher = teacherRepository.findById(id);

        Teacher dbTeacher = null;
        if(teacher.isPresent()){
            dbTeacher = teacher.get();
            return new TeacherDTO(dbTeacher.getId(), dbTeacher.getFirstName(), dbTeacher.getLastName());
        }

        throw new EntityNotFound("Teacher not found");
    }

    @Transactional
    @Override
    public TeacherDTO getTeacherCourses(long id){
        Optional<Teacher> existingTeacher = teacherRepository.findById(id);

        if(existingTeacher.isPresent()){
            Teacher dbTeacher = existingTeacher.get();
            List<Course> teacherCourses = courseRepository.findByTeacherId(id);

            if(!teacherCourses.isEmpty()){
                List<CourseForTeacherDTO> courses = teacherCourses.stream()
                        .map(course -> new CourseForTeacherDTO(course.getId(), course.getName(), course.getDescription(), course.getImageUrl()))
                                .collect(Collectors.toList());

                return new TeacherDTO(dbTeacher.getId(), dbTeacher.getFirstName(), dbTeacher.getLastName(), courses);
            }
        }

        throw new EntityNotFound("Teacher not found");
    }

    @Transactional
    @Override
    public Teacher createTeacher(Teacher teacher){
        if(!verifyPassword(teacher.getPassword())){
            throw new IllegalArgumentException("Password must be 6-20 characters long, contain at least one uppercase letter, and at least one lowercase letter.");
        }

        String encodedPassword = passwordEncoder.encode(teacher.getPassword());
        teacher.setPassword(encodedPassword);

        return teacherRepository.save(teacher);
    }

    public boolean verifyPassword(String password){
        String regex = "^(?=.*[a-z])(?=.*[A-Z]).{6,20}$";
        return password != null && password.matches(regex);
    }

    @Transactional
    @Override
    public Teacher updateTeacher(long id, Teacher teacher){
        Optional<Teacher> existingTeacher = teacherRepository.findById(id);

        if(existingTeacher.isPresent()){
            Teacher dbTeacher = existingTeacher.get();
            if(teacher.getFirstName() != null){
                dbTeacher.setFirstName(teacher.getFirstName());
            }
            if(teacher.getLastName() != null){
                dbTeacher.setLastName(teacher.getLastName());
            }
            if (teacher.getEmail() != null) {
                dbTeacher.setEmail(teacher.getEmail());
            }

            return teacherRepository.save(dbTeacher);
        }

        throw  new EntityNotFound("Teacher not found");
    }

    @Transactional
    @Override
    public String removeById(long id){
        Optional<Teacher> existingTeacher = teacherRepository.findById(id);

        if(existingTeacher.isPresent()){
            teacherRepository.deleteById(id);
            return "Teacher deleted successfully";
        }

        return null;
    }
}
