package com.coursemy_backend.coursemy.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name="first_name")
    @NotEmpty(message = "First name cannot be empty")
    @NotNull(message = "First name is required")
    private String firstName;

    @Column(name="last_name")
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
    private String role = "ROLE_STUDENT";

    public Student(){

    }

    @ManyToMany(mappedBy = "students")
    private List<Course> courses = new ArrayList<>();

    public Student(String firstName, String lastName, String email, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = "ROLE_STUDENT";
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

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getEmail(){
        return this.email;
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

    public String getRole(){
        return this.role;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
