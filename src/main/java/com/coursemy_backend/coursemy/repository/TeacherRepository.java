package com.coursemy_backend.coursemy.repository;

import com.coursemy_backend.coursemy.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}
