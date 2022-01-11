package com.skm.controller;

import com.skm.model.LoginRequest;
import com.skm.service.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Saroj on 11/01/22
 **/
@RestController
@RequestMapping("/api/1.0/job")
public class JobController {
    Logger log = LoggerFactory.getLogger(JobController.class.getName());
    @GetMapping("/searchJob")
    public String searchJob() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        log.info("Userdetails:{}",userDetails.getEmail());
        return "Searching Jobs";
    }
}
