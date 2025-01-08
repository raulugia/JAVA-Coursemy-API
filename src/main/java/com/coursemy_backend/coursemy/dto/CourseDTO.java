package com.coursemy_backend.coursemy.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CourseDTO {
    @Size(min = 5, max = 50, message = "Name must be between 5 and 50 characters")
    @NotEmpty(message = "Course name cannot be empty")
    @NotNull(message = "Course name is required")
    private String name;

    @Size(min = 50, max = 1000, message = "Description must be between 100 and 1000 characters")
    @NotEmpty(message = "Description cannot be empty")
    @NotNull(message = "Description is required")
    private String description;

    private String imageUrl;

    @Min(value = 1, message = "Teacher id cannot be smaller than 1")
    @NotNull(message = "Teacher id is required")
    private long teacherId;

    private TeacherDTO teacher;

    public CourseDTO(String name, String description, String imageUrl, long teacherId, TeacherDTO teacher) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.teacherId = teacherId;
        this.teacher = teacher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
    }

    public TeacherDTO getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherDTO teacher) {
        this.teacher = teacher;
    }

}
