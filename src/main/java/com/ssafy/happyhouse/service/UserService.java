package com.ssafy.happyhouse.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ssafy.happyhouse.dto.user.TokenDto;
import com.ssafy.happyhouse.dto.user.UserDto;
import com.ssafy.happyhouse.dto.user.UserUpdateDto;
import com.ssafy.happyhouse.entity.User;
import com.ssafy.happyhouse.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Slf4j
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

    public UserDto getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("사용자 정복가 존재하지 않습니다."));
        return UserDto.builder()
                .id(user.getId())
                .userId(user.getUserId())
                .password(user.getPassword())
                .nickName(user.getNickName())
                .email(user.getEmail())
                .sidoName(user.getSidoName())
                .build();
    }

    @Transactional
    public TokenDto updateUser(Long id, UserUpdateDto updateDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("사용자 정복가 존재하지 않습니다."));
        user.setPassword(passwordEncoder.encode(updateDto.getPassword()));
        user.setEmail(updateDto.getEmail());
        user.setNickName(updateDto.getNickName());
        user.setSidoName(updateDto.getSidoName());

        String token = JWT.create()
                .withSubject("cosToken")
                .withExpiresAt(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24)))
                .withClaim("userNo", user.getId())
                .withClaim("userId", user.getUserName())
                .withClaim("nickName", user.getNickName())
                .withClaim("sidoName", user.getSidoName())
                .sign(Algorithm.HMAC512("cos"));
        return new TokenDto(token);
    }

    @Transactional
    public int deleteUser(Long id) {
        log.info("회원탈퇴 서비스 요청");
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("사용자 정복가 존재하지 않습니다."));
        userRepository.delete(user);
        return 1;
    }
}
