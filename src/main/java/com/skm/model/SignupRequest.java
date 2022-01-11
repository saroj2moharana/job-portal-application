package com.skm.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

/**
 * Created by  on 11/01/22
 **/
public class SignupRequest {
    private String username;
    private String email;
    private CharSequence password;
    @JsonProperty("roles")
    private Set<String> roles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CharSequence getPassword() {
        return password;
    }

    public void setPassword(CharSequence password) {
        this.password = password;
    }

    public Set<String> getRole() {
        return roles;
    }

    public void setRole(Set<String> role) {
        this.roles = role;
    }

    @Override
    public String toString() {
        return "SignupRequest{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password=" + password +
                ", roles=" + roles +
                '}';
    }
}
