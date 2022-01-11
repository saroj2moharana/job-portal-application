package com.skm.controller;
import com.skm.model.*;
import com.skm.service.LoginService;
import com.skm.service.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Created by Saroj on 11/01/22
 **/
@RestController
@RequestMapping("/api/1.0/auth")
public class LoginController {
    Logger logger = LoggerFactory.getLogger(LoginController.class.getName());
    @GetMapping({ "/login" })
    public String login() {
        return "Hello World";
    }

    @Autowired
    LoginService loginService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(loginService.authenticate(loginRequest));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        logger.info("SignupRequest:{}",signUpRequest);
        return ResponseEntity.ok(loginService.registerUser(signUpRequest));
    }
}
