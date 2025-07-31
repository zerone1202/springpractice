package org.example.springpractice.controller;

import lombok.RequiredArgsConstructor;
import org.example.springpractice.dto.UserRequest;
import org.example.springpractice.dto.UserResponse;
import org.example.springpractice.repository.UserRepository;
import org.example.springpractice.service.MemberService;
import org.example.springpractice.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final MemberService memberService;

    @PostMapping("/users")
    public UserResponse createUser(
            @RequestBody UserRequest userRequest
    ) {
        return userService.createUser(userRequest);
    }

    @GetMapping("/users")
    public List<UserResponse> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/users/{userId}")
    public UserResponse getUser(
            @PathVariable("userId") Long userId
    ) {
        return userService.getUser(userId);
    }

    @PutMapping("/users/{userId}")
    public UserResponse updateUser(
            @PathVariable Long userId,
            @RequestBody UserRequest userRequest
    ) {
        return userService.updateUser(userId, userRequest);
    }

    @DeleteMapping("/users/{userId}")
    public void deleteUser(
            @PathVariable Long userId
    ) {
        userService.deleteUser(userId);
    }
}
