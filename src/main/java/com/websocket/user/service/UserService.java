package com.websocket.user.service;

import com.websocket.user.entity.User;
import com.websocket.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public User findVerifiedUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("USER_NOT_FOUND"));
    }
}
