package com.hoperise.security.controllers;

import com.hoperise.security.dto.UserResponse;
import com.hoperise.security.models.User;
import com.hoperise.security.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/doctors")
    public ResponseEntity<List<UserResponse>> getAllDoctors() {
        return ResponseEntity.ok(userService.getAllDoctors());
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/patients")
    public ResponseEntity<List<UserResponse>> getAllPatients() {
        return ResponseEntity.ok(userService.getAllPatients());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/uuid/{uuid}")
    public ResponseEntity<String> deleteUserByUUID(@PathVariable String uuid) {
        return ResponseEntity.ok(userService.deleteUserByUUID(uuid));
    }

    @GetMapping("/{userId}")
    public UserResponse getUserById(@PathVariable Integer userId) {
        User user = userService.findUserById(userId);
        return new UserResponse(user);
    }
}