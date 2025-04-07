package com.movieplan;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.movieplan.controller.AdminController;
import com.movieplan.model.LoginRequest;

class AdminControllerTest {

    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin@123";

    @Test
    void testAdminLoginSuccess() {
        AdminController adminController = new AdminController();
        LoginRequest loginRequest = new LoginRequest(ADMIN_USERNAME, ADMIN_PASSWORD);

        ResponseEntity<Object> response = adminController.adminLogin(loginRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("{\"result\": \"success\"}", response.getBody());
    }

    @Test
    void testAdminLoginFailure() {
        AdminController adminController = new AdminController();
        LoginRequest loginRequest = new LoginRequest("wrongUsername", "wrongPassword");

        ResponseEntity<Object> response = adminController.adminLogin(loginRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("{\"result\": \"failure\"}", response.getBody());
    }
}
