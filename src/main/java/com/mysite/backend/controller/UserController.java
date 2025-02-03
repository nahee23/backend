package com.mysite.backend.controller;

import com.mysite.backend.exception.UserNotFoundException;
import com.mysite.backend.model.User;
import com.mysite.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@ResponseBody 포함
@RestController
@CrossOrigin("*")
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

    @GetMapping("/users/{id}")
    User getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PutMapping("/users/{id}")
    User updateUser(@PathVariable Long id, @RequestBody User newUser) {
        //DB 에서 user 를 찾음 (없으면 예외처리)
        return userRepository.findById(id)
                .map(user -> {
                    //찾은 유저에 새유저 내용으로 업데이트
                    user.setName(newUser.getName());
                    user.setEmail(newUser.getEmail());
                    user.setUsername(newUser.getUsername());
                    return userRepository.save(user); //업데이트 된 유저를 저장(업데이트)
                }).orElseThrow(()-> new UserNotFoundException(id));
    }

    @DeleteMapping("/users/{id}")
    String deleteUser(@PathVariable Long id) {
        //없을 경우에는 예외처리
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id); //삭제
        return "유저아이디: " + id + "는 삭제 되었습니다.";
    }
}
