package com.coursemy_backend.coursemy.repository;

import com.coursemy_backend.coursemy.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
