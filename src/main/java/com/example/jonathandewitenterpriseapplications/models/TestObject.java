package com.example.jonathandewitenterpriseapplications.models;

import javax.persistence.Column;

public class TestObject {

    private String username;


    private String firstname;

    public TestObject(String username, String firstname) {
        this.username = username;
        this.firstname = firstname;
    }

    public TestObject() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
}
