package com.movieplan.controller;

import com.movieplan.dto.AdminLoginRequest;
import com.movieplan.dto.AdminLoginResponse;
import com.movieplan.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/login")
    public ResponseEntity<AdminLoginResponse> adminLogin(@RequestBody AdminLoginRequest loginRequest) {
        return ResponseEntity.ok(adminService.login(loginRequest));
    }
}
