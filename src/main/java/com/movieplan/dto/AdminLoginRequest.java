package com.movieplan.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdminLoginRequest {
    private String username;
    private String password;

    public AdminLoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
