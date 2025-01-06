package com.coursemy_backend.coursemy.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

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

    public Student(){

    }

    @ManyToMany(mappedBy = "students")
    private List<Course> courses = new ArrayList<>();

    public Student(String firstName, String lastName, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
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

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
