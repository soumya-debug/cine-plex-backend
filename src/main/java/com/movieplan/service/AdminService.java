package com.movieplan.service;

import com.movieplan.dto.AdminLoginRequest;
import com.movieplan.dto.AdminLoginResponse;

public interface AdminService {
    AdminLoginResponse login(AdminLoginRequest loginRequest);
}

