package com.leave.dto;

import lombok.Data;

@Data
public class AuthResponse {
	private String token;

    // Constructor
    public AuthResponse(String token) {
        this.token = token;
    }

    // Getter & Setter
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
