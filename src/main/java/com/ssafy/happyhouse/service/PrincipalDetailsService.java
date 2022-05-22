package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.entity.User;
import com.ssafy.happyhouse.jwt.PrincipalDetails;
import com.ssafy.happyhouse.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserId(username).orElseThrow(() -> new UsernameNotFoundException("사용자의 Id가 존재하지 않습니다."));
        return new PrincipalDetails(user);
    }
}
