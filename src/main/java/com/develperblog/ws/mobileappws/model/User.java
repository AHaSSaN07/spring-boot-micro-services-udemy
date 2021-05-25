package com.develperblog.ws.mobileappws.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class User {

    @NotNull(message = "First Name cannot be null")
    private String firstName;
    private String lastName;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Email
    private String email;

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
