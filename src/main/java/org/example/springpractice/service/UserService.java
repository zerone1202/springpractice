package org.example.springpractice.service;

import lombok.RequiredArgsConstructor;
import org.example.springpractice.dto.UserRequest;
import org.example.springpractice.dto.UserResponse;
import org.example.springpractice.entity.User;
import org.example.springpractice.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponse createUser(UserRequest userRequest) {
        User user = new User(userRequest.getUsername());
        User savedUser = userRepository.save(user);

        return new UserResponse(
                savedUser.getId(),
                savedUser.getUsername()
        );
    }

    @Transactional(readOnly = true)
    public UserResponse getUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("그런 id의 유저는 없습니다.")
        );

        return new UserResponse(
                user.getId(),
                user.getUsername()
        );
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getUsers() {
        List<User> users = userRepository.findAll();

        List<UserResponse> dtos = new ArrayList<>();

        for (User user : users) {
            dtos.add(new UserResponse(user.getId(), user.getUsername()));
        }
        return dtos;
    }

    @Transactional
    public UserResponse updateUser(Long id, UserRequest userRequest) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("그런 id의 유저는 없습니다.")
        );
        user.updateUsername(userRequest.getUsername());

        return new UserResponse(user.getId(), user.getUsername());
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("그런 id의 유저는 없습니다.")
        );
        userRepository.deleteById(id);
    }
}

