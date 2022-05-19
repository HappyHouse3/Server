package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.dto.user.UserInputDto;
import com.ssafy.happyhouse.entity.User;
import com.ssafy.happyhouse.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void join(UserInputDto userDto) {
        User user = new User(userDto.getUserId(), userDto.getPassword());
        userRepository.save(user);
    }

    public User findUser(Integer id) {
        return userRepository.findById(id);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public void updateUser(Integer id, User updateDto) {
        User user = userRepository.findById(id);
        user.setPassword(updateDto.getPassword());
    }
}
