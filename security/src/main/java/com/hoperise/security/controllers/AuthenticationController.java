package com.hoperise.security.controllers;

import com.hoperise.security.dto.*;
import com.hoperise.security.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@RestController
@RequestMapping(path="/auth")
public class AuthenticationController {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


    @PostMapping("/register-patient")
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody RegisterRequest registerRequest) {

        return new ResponseEntity<>(authenticationService.register(registerRequest,"PATIENT"), HttpStatus.CREATED);
    }

    @PostMapping("/user/update")
    public ResponseEntity<AuthenticationResponse> updateUser(@Valid @RequestBody UserUpdateRequest userUpdateRequest) {

        return new ResponseEntity<>(authenticationService.updateUser(userUpdateRequest), HttpStatus.OK);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/register-admin")
    public ResponseEntity<AuthenticationResponse> registerAdmin(@Valid @RequestBody RegisterRequest registerRequest) {

        return new ResponseEntity<>(authenticationService.register(registerRequest,"ADMIN"), HttpStatus.CREATED);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/register-doctor")
    public ResponseEntity<AuthenticationResponse> registerDoctor(@Valid @RequestBody RegisterRequest registerRequest) {

        return new ResponseEntity<>(authenticationService.register(registerRequest,"DOCTOR"), HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@Valid @RequestBody AuthenticationCredentials credentials) {

        return ResponseEntity.ok(authenticationService.authenticate(credentials));

    }

    @PostMapping("/refresh-token")
    public ResponseEntity<AuthenticationResponse> refresh(HttpServletRequest request, HttpServletResponse response) {
        try {
            return ResponseEntity.ok(authenticationService.refreshToken(request,response));
        } catch (IOException e) {
            logger.error("Failed to refresh token");
            return (ResponseEntity<AuthenticationResponse>) ResponseEntity.badRequest();
        }
    }

    @PostMapping("/validate-token")
    public ResponseEntity<ValidationResponse> validateToken(HttpServletRequest request) {
        return ResponseEntity.ok(authenticationService.validateToken(request));
    }

    @GetMapping("/uuid-token/{uuid}")
    public ResponseEntity<String> getTokenFromUUID(@PathVariable String uuid) {
        return ResponseEntity.ok(authenticationService.getTokenFromUUID(uuid));
    }

}