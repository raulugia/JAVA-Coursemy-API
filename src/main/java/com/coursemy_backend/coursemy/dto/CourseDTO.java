package com.coursemy_backend.coursemy.dto;

import com.coursemy_backend.coursemy.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CourseDTO {
    @JsonView(Views.Basic.class)
    private long id;

    @Size(min = 5, max = 50, message = "Name must be between 5 and 50 characters")
    @NotEmpty(message = "Course name cannot be empty")
    @NotNull(message = "Course name is required")
    @JsonView(Views.Basic.class)
    private String name;

    @Size(min = 50, max = 1000, message = "Description must be between 100 and 1000 characters")
    @NotEmpty(message = "Description cannot be empty")
    @NotNull(message = "Description is required")
    @JsonView(Views.Basic.class)
    private String description;

    @JsonView(Views.Basic.class)
    private String imageUrl;

    @Min(value = 1, message = "Teacher id cannot be smaller than 1")
    @NotNull(message = "Teacher id is required")
    @JsonView(Views.Detailed.class)
    private long teacherId;

    @JsonView(Views.Basic.class)
    private TeacherDTO teacher;

    public CourseDTO(){

    }

    public CourseDTO(long id, String name, String description, String imageUrl, TeacherDTO teacher) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.teacher = teacher;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
