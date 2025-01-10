package com.coursemy_backend.coursemy.dto;

import com.coursemy_backend.coursemy.views.Views;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;

public class StudentDTO {
    @JsonView(Views.Basic.class)
    private long id;

    @JsonView(Views.Basic.class)
    private String firstName;

    @JsonView(Views.Basic.class)
    private String lastName;

    @JsonView(Views.Detailed.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String email;

    public StudentDTO(long id, String firstName, String lastName){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public StudentDTO(long id, String firstName, String lastName, String email){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
