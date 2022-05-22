package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.dto.user.UserDto;
import com.ssafy.happyhouse.entity.User;
import com.ssafy.happyhouse.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public UserDto join(UserDto userDto) {
        if (userRepository.findByUserId(userDto.getUserId()).orElse(null) != null) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }

        User user = User.builder()
                .userName(userDto.getUserId())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .nickName(userDto.getNickName())
                .email(userDto.getEmail())
                .sidoName(userDto.getSidoName())
                .build();

        User saveUser = userRepository.save(user);

        UserDto userReturnDto = new UserDto();
        userReturnDto.setId(saveUser.getId());
        userReturnDto.setUserId(saveUser.getUserId());
        userReturnDto.setPassword(saveUser.getPassword());
        userReturnDto.setNickName(saveUser.getNickName());
        userReturnDto.setRoles(saveUser.getRoleList());
        userReturnDto.setEmail(saveUser.getEmail());
        userReturnDto.setSidoName(saveUser.getSidoName());

        return userReturnDto;
    }

    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }
}
