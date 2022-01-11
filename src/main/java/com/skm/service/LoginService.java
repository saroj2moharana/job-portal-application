package com.skm.service;

import com.skm.config.JwtResponse;
import com.skm.config.JwtUtils;
import com.skm.constants.ERole;
import com.skm.dao.UserDAOServiceImpl;
import com.skm.dao.UserRepository;
import com.skm.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Saroj on 11/01/22
 **/
@Service
public class LoginService {
    @Autowired
    UserDAOServiceImpl userDAOService;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtils jwtUtils;

    public MessageResponse registerUser(SignupRequest signUpRequest){
        User user = userDAOService.getUserByUsername(signUpRequest.getUsername());
        if(user != null) return new MessageResponse("Error: Username is already taken!");

        user = userDAOService.getUserByEmail(signUpRequest.getEmail());
        if(user != null) new MessageResponse("Error: Email is already in use!");


        // Create new user's account
        user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<String> roles = new HashSet<>();

        if (strRoles == null) {
            roles.add(ERole.ROLE_USER.name());
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        roles.add(ERole.ROLE_ADMIN.name());
                        break;
                    case "user":
                        roles.add(ERole.ROLE_USER.name());
                        break;
                    default:
                        new RuntimeException("Error: Role is not found.");
                }
            });
        }

        user.setRoles(roles);
        userDAOService.storeUser(user);

        return new MessageResponse("User registered successfully!");
    }

    public JwtResponse authenticate(LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());


        return new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles);
    }
}
