package com.movieplan.service.impl;

import com.movieplan.config.JwtUtil;
import com.movieplan.dto.AdminLoginRequest;
import com.movieplan.dto.AdminLoginResponse;
import com.movieplan.service.AdminService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Value("${admin.username}")
    private String adminUsername;

    @Value("${admin.password}")
    private String adminPassword;

    private final JwtUtil jwtUtil;

    public AdminServiceImpl(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public AdminLoginResponse login(AdminLoginRequest request) {
        if (adminUsername.equals(request.getUsername()) && adminPassword.equals(request.getPassword())) {
            String token = jwtUtil.generateToken(request.getUsername());
            return new AdminLoginResponse("success", "Welcome admin!", token);
        } else {
            return new AdminLoginResponse("failure", "Invalid credentials", null);
        }
    }
}

