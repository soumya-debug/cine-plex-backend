//package com.movieplan;
//
//import com.movieplan.controller.AdminController;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.ResponseEntity;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@SpringBootTest
//class AdminControllerTest {
//
//    @Autowired
//    private AdminController adminController;
//
//    @Value("${admin.username}")
//    private String adminUsername;
//
//    @Value("${admin.password}")
//    private String adminPassword;
//
//    @Test
//    void testAdminLoginSuccess() {
//        Map<String, String> loginRequest = new HashMap<>();
//        loginRequest.put("username", adminUsername);
//        loginRequest.put("password", adminPassword);
//
//        ResponseEntity<Map<String, String>> response = adminController.adminLogin(loginRequest);
//
//        assertEquals("success", response.getBody().get("result"));
//    }
//
//    @Test
//    void testAdminLoginFailure() {
//        Map<String, String> loginRequest = new HashMap<>();
//        loginRequest.put("username", "wrongUsername");
//        loginRequest.put("password", "wrongPassword");
//
//        ResponseEntity<Map<String, String>> response = adminController.adminLogin(loginRequest);
//
//        assertEquals("failure", response.getBody().get("result"));
//    }
//}
