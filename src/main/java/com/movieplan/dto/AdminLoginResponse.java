package com.movieplan.dto;

public class AdminLoginResponse {
    private String token;
    private String result;
    private String message;

    public AdminLoginResponse(String result, String message, String token) {
        this.result = result;
        this.message = message;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
