package org.example.notificationservice.model;

public class UserEvent {
    private String email;
    private String operation;

    public UserEvent(String email, String operation) {
        this.email = email;
        this.operation = operation;
    }

    public UserEvent() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}