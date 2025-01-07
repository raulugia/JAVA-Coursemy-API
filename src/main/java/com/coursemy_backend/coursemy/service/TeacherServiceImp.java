package com.coursemy_backend.coursemy.service;

import com.coursemy_backend.coursemy.entities.Teacher;
import com.coursemy_backend.coursemy.repository.TeacherRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImp implements TeacherService {
    private TeacherRepository teacherRepository;

    @Autowired
    public TeacherServiceImp(TeacherRepository teacherRepository){
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<Teacher> getAllTeachers(){
        return teacherRepository.findAll();
    }

    @Override
    public Teacher getById(long id){
        Optional<Teacher> teacher = teacherRepository.findById(id);

        Teacher dbTeacher = null;
        if(teacher.isPresent()){
            dbTeacher = teacher.get();
            return dbTeacher;
        }

        return dbTeacher;
    }

    @Override
    public Teacher createTeacher(Teacher teacher){
        return teacherRepository.save(teacher);
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

        return null;
    }

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
