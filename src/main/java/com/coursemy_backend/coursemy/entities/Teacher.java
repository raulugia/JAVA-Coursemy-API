package com.coursemy_backend.coursemy.entities;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="teacher")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name="first_name")
    @Size(min = 1, max = 20, message = "First name must be between 1 and 20 characters")
    @NotEmpty(message = "First name cannot be empty")
    @NotNull(message = "First name is required")
    private String firstName;

    @Column(name="last_name")
    @Size(min = 1, max = 20, message = "Last name must be between 1 and 20 characters")
    @NotEmpty(message = "Last name cannot be empty")
    @NotNull(message = "Last name is required")
    private String lastName;

    @Email
    @Column(name="email")
    @NotEmpty(message = "Email cannot be empty")
    @NotNull(message = "Email is required")
    @Size(min = 5, max = 254, message = "Email must be between 5 and 254 characters")
    private String email;

    @Column(name = "password")
    @NotNull(message = "Password is required")
    @NotEmpty(message = "Password cannot be empty")
    private String password;

    @Column(name = "role")
    private String role;


    @OneToMany(mappedBy = "teacher", fetch=FetchType.LAZY, cascade={CascadeType.REMOVE})
    private List<Course> courses = new ArrayList<>();

    public Teacher(){

    }

    public Teacher(String firstName, String lastName, String email, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = "TEACHER";
    }

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role){
        this.role = role;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", courses=" + courses +
                '}';
    }
}
