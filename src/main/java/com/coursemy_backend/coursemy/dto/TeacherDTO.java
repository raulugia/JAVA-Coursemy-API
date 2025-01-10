package com.coursemy_backend.coursemy.dto;

import com.coursemy_backend.coursemy.views.Views;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.List;

public class TeacherDTO {
    @JsonView(Views.Basic.class)
    private long id;

    @JsonView(Views.Basic.class)
    private String firstName;

    @JsonView(Views.Basic.class)
    private String lastName;

    @JsonView(Views.Detailed.class)
    private String email;

    @JsonView(Views.Basic.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<CourseForTeacherDTO> courses;

    public TeacherDTO(long id, String firstName, String lastName){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public TeacherDTO(long id, String firstName, String lastName, String email){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public TeacherDTO(long id, String firstName, String lastName, List<CourseForTeacherDTO> courses){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.courses = courses;
    }

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<CourseForTeacherDTO> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseForTeacherDTO> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "TeacherDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", courses=" + courses.toString() +
                '}';
    }
}
