package com.coursemy_backend.coursemy.dto;

import com.coursemy_backend.coursemy.views.Views;
import com.fasterxml.jackson.annotation.JsonView;

public class CourseForTeacherDTO {
    @JsonView(Views.Basic.class)
    public long id;

    @JsonView(Views.Basic.class)
    private String name;

    @JsonView(Views.Basic.class)
    private String description;

    @JsonView(Views.Basic.class)
    private String imageUrl;

    public CourseForTeacherDTO(long id, String name, String description, String imageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
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

    @Override
    public String toString() {
        return "CourseForTeacherDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
