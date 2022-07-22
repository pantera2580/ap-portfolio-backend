package com.mec.apportfoliobackend.security.user;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

public class UserRequest {
    private UUID id;
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
    @NotEmpty
    private String username;

    public UserRequest() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
