package com.movieplan.controller;

import com.movieplan.config.JwtUtil;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/token")
public class TokenController {

    private final JwtUtil jwtUtil;

    public TokenController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/generate")
    public String generateToken(@RequestParam(defaultValue = "tester") String username) {
        return jwtUtil.generateToken(username);
    }
}
