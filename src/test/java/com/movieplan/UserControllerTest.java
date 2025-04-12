package com.movieplan;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.movieplan.config.JwtUtil;
import com.movieplan.controller.UserController;
import com.movieplan.model.User;
import com.movieplan.model.UserLogin;
import com.movieplan.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

class UserControllerTest {

    private UserController userController;
    private UserRepository uRepo;
    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        uRepo = mock(UserRepository.class);
        jwtUtil = mock(JwtUtil.class);
        userController = new UserController();
        injectDependencies(userController, uRepo, jwtUtil);
    }

    private void injectDependencies(UserController controller, UserRepository repo, JwtUtil jwtUtil) {
        try {
            Field repoField = UserController.class.getDeclaredField("userRepository");
            repoField.setAccessible(true);
            repoField.set(controller, repo);

            Field jwtField = UserController.class.getDeclaredField("jwtUtil");
            jwtField.setAccessible(true);
            jwtField.set(controller, jwtUtil);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testAuthenticateSuccess() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("secret");

        when(uRepo.findByEmail("test@example.com")).thenReturn(user);
        when(jwtUtil.generateToken("test@example.com")).thenReturn("mockedToken");

        UserLogin login = new UserLogin();
        login.setEmail("test@example.com");
        login.setPassword("secret");

        ResponseEntity<?> response = userController.authenticate(login);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Map<String, Object> body = (Map<String, Object>) response.getBody();
        assertNotNull(body.get("token"));
        assertEquals("mockedToken", body.get("token"));
        assertEquals(user, body.get("user"));
    }

    // Other tests stay the same as your original versions...

    // Just to show one unchanged:
    @Test
    void testAuthenticateEmailNotFound() {
        when(uRepo.findByEmail("missing@example.com")).thenReturn(null);

        UserLogin login = new UserLogin();
        login.setEmail("missing@example.com");
        login.setPassword("any");

        ResponseEntity<?> response = userController.authenticate(login);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Email not found", ((Map<?, ?>) response.getBody()).get("error"));
    }
}
