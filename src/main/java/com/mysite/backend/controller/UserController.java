package com.mysite.backend.controller;

import com.mysite.backend.model.User;
import com.mysite.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@ResponseBody 포함
@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/users")
    User addUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/users")
    List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
