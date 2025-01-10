package com.coursemy_backend.coursemy.repository;

import com.coursemy_backend.coursemy.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CourseRepository extends JpaRepository<Course, Long> {

}
