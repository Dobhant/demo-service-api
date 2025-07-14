package org.example.demoserviceapi.kafka;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserEvent {

    private String email;
    private String operation;

    @JsonCreator
    public UserEvent(
            @JsonProperty("email") String email,
            @JsonProperty("operation") String operation
    ) {
        this.email = email;
        this.operation = operation;
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

    @Override
    public String toString() {
        return "UserEvent{" +
                "email='" + email + '\'' +
                ", operation='" + operation + '\'' +
                '}';
    }
}
