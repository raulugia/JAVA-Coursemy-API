package com.coursemy_backend.coursemy.service;

import com.coursemy_backend.coursemy.dto.CourseForTeacherDTO;
import com.coursemy_backend.coursemy.dto.TeacherDTO;
import com.coursemy_backend.coursemy.entities.Course;
import com.coursemy_backend.coursemy.entities.Teacher;
import com.coursemy_backend.coursemy.exception.EntityNotFound;
import com.coursemy_backend.coursemy.exception.PasswordValidationException;
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

    @Autowired
    public TeacherServiceImp(TeacherRepository teacherRepository, PasswordEncoder passwordEncoder){
        this.teacherRepository = teacherRepository;
        this.passwordEncoder = passwordEncoder;
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
            List<Course> teacherCourses = dbTeacher.getCourses();

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
    public TeacherDTO createTeacher(Teacher teacher){
        if(!verifyPassword(teacher.getPassword())){
            throw new PasswordValidationException();
        }

        String encodedPassword = passwordEncoder.encode(teacher.getPassword());
        teacher.setPassword(encodedPassword);

        Teacher dbTeacher = teacherRepository.save(teacher);

        return new TeacherDTO(dbTeacher.getId(), dbTeacher.getFirstName(), dbTeacher.getLastName(), dbTeacher.getEmail());
    }

    public boolean verifyPassword(String password){
        String regex = "^(?=.*[a-z])(?=.*[A-Z]).{6,20}$";
        return password != null && password.matches(regex);
    }

    @Transactional
    @Override
    public TeacherDTO updateTeacher(long id, Teacher teacher){
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

            Teacher updatedTeacher = teacherRepository.save(dbTeacher);

            return new TeacherDTO(updatedTeacher.getId(), updatedTeacher.getFirstName(), updatedTeacher.getLastName(), updatedTeacher.getEmail());
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

        throw new EntityNotFound("Teacher not found");
    }
}
