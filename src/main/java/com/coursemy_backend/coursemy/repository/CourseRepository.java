package com.coursemy_backend.coursemy.repository;

import com.coursemy_backend.coursemy.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByTeacherId(long id);
}
