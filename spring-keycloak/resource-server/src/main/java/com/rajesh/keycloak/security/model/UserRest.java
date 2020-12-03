package com.rajesh.keycloak.security.model;

public class UserRest {
    private String id;
    private String firstName;
    private String lastName;

    public UserRest() {
    }

    public UserRest(String userId, String firstName, String lastName) {
        this.id = userId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
}
