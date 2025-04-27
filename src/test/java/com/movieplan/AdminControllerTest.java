package com.movieplan;

import com.movieplan.controller.AdminController;
import com.movieplan.dto.AdminLoginRequest;
import com.movieplan.dto.AdminLoginResponse;
import com.movieplan.service.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class AdminControllerTest {

    @InjectMocks
    private AdminController adminController;

    @Mock
    private AdminService adminService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAdminLoginSuccess() {
        // Prepare test data
        AdminLoginRequest loginRequest = new AdminLoginRequest();
        loginRequest.setUsername("admin");
        loginRequest.setPassword("admin123");
        AdminLoginResponse expectedResponse = new AdminLoginResponse();
        expectedResponse.setToken("mocked-jwt-token");

        // Mock behavior
        when(adminService.login(loginRequest)).thenReturn(expectedResponse);

        // Execute controller method
        ResponseEntity<AdminLoginResponse> response = adminController.adminLogin(loginRequest);

        // Validate
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("mocked-jwt-token", response.getBody().getToken());
    }
}