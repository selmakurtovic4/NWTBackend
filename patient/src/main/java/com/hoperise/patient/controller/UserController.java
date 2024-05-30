//package com.hoperise.patient.controller;
//
//import com.hoperise.patient.model.User;
//import com.hoperise.patient.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/users")
//public class UserController {
//
//    @Autowired
//    private  UserRepository userRepository;
//
//    @GetMapping
//    public List<User> getAllUsers() {
//        return userRepository.getAll();
//    }
//
//    @GetMapping("/{id}")
//    public User getById(@PathVariable("id") Long id) {
//        return userRepository.findById(id).get();
//    }
//
//    @PostMapping
//    public User createUser(@RequestBody User user) {
//        return userRepository.save(user);
//    }
//
//}
