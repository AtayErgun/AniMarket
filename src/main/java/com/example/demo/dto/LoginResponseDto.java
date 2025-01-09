package com.example.demo.dto;

import com.example.demo.entity.Role;

public class LoginResponseDto {
    private String message;
    private boolean success;
    private Role role;

    public LoginResponseDto(String message, boolean success, Role role) {
        this.message = message;
        this.success = success;
        this.role = role;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
