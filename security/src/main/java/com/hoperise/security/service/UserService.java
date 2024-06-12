package com.hoperise.security.service;

import com.hoperise.security.dto.UserResponse;
import com.hoperise.security.models.User;
import com.hoperise.security.repositories.UserRepository;
import com.hoperise.security.utils.DuplicateEntryException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;


    private  void checkIfUserUnique(String username, String email) {
        userRepository.findByUsername(username)
                .ifPresent(u-> {throw new DuplicateEntryException("Username " + username + " is already taken.");});
        userRepository.findByEmail(email)
                .ifPresent(u-> {throw new DuplicateEntryException("An account using that email address already exists.");});
    }

    public List<UserResponse> getAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }

    public List<UserResponse> getAllDoctors() {
        return userRepository
                .getAllDoctors()
                .stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }

    public List<UserResponse> getAllPatients() {
        return userRepository
                .getAllPatients()
                .stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }

    public String deleteUser(Integer id){
        var us = userRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("User with id " + id + " does not exist!"));
        userRepository.deleteById(id);
        return "User with id " + id + " is successfully deleted!";
    }

    public String deleteUserByUUID(String uuid){
        var us = userRepository.findByUUID(uuid).orElseThrow(()-> new EntityNotFoundException("User with id " + uuid + " does not exist!"));
        userRepository.delete(us);
        return "User with UUID " + uuid + " is successfully deleted!";
    }

    public User findUserById(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new EntityNotFoundException("User with id " + userId + " does not exist!"));
        return user;
    }
}