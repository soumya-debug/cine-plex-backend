package com.movieplan.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdminLoginResponse {
    private String token;
    private String result;
    private String message;

    public AdminLoginResponse(String result, String message, String token) {
        this.result = result;
        this.message = message;
        this.token = token;
    }
}
