package com.websocket.user.service;

import com.websocket.user.dto.UserDto;
import com.websocket.user.entity.User;
import com.websocket.user.mapper.UserMapper;
import com.websocket.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Transactional
    public User createUser(UserDto.Post postDto) {
        User user = userMapper.userPostDtoToUser(postDto);
        return userRepository.save(user);
    }

    public User findVerifiedUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("USER_NOT_FOUND"));
    }
}
