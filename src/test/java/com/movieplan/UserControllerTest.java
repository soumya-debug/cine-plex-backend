package com.movieplan;

import com.movieplan.controller.UserController;
import com.movieplan.dto.UserLoginRequest;
import com.movieplan.model.User;
import com.movieplan.service.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    private UserController userController;
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = mock(UserService.class);
        userController = new UserController();
        injectService(userController, userService);
    }

    private void injectService(UserController controller, UserService service) {
        try {
            var field = UserController.class.getDeclaredField("userService");
            field.setAccessible(true);
            field.set(controller, service);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testAuthenticateSuccess() {
        UserLoginRequest login = new UserLoginRequest("test@example.com", "secret");
        User mockUser = new User();
        mockUser.setEmail("test@example.com");
        mockUser.setPassword("secret");

        Map<String, Object> mockResponse = Map.of(
                "token", "mockedToken",
                "user", mockUser
        );

        Mockito.<ResponseEntity<?>>when(userService.authenticate(login))
                .thenReturn(ResponseEntity.ok(mockResponse));

        ResponseEntity<?> response = userController.authenticate(login);

        assertEquals(200, response.getStatusCodeValue());
        Map<String, Object> body = (Map<String, Object>) response.getBody();
        assertEquals("mockedToken", body.get("token"));
        assertEquals(mockUser, body.get("user"));
    }


    @Test
    void testAuthenticateEmailNotFound() {
        UserLoginRequest login = new UserLoginRequest("missing@example.com", "any");

        when(userService.authenticate(login))
                .thenReturn(ResponseEntity.status(401).body(Map.of("error", "Email not found")));

        ResponseEntity<?> response = userController.authenticate(login);

        assertEquals(401, response.getStatusCodeValue());
        assertEquals("Email not found", ((Map<?, ?>) response.getBody()).get("error"));
    }
}
