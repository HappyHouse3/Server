package com.ssafy.happyhouse.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ssafy.happyhouse.entity.User;
import com.ssafy.happyhouse.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final UserRepository userRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
        System.out.println("JwtAuthorizationFilter");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("권한이나 인증 요청 필요!");

        String jwtHeader = request.getHeader("Authorization");
        System.out.println("jwtHeader = " + jwtHeader);

        if(jwtHeader == null || !jwtHeader.startsWith("Bearer")) {
            System.out.println("1");
            chain.doFilter(request, response);
            return;
        }

        String token = jwtHeader.replace("Bearer ", "");

        Long id = JWT.require(Algorithm.HMAC512("cos")).build().verify(token).getClaim("id").asLong();

        // 서명이 정상적으로 됨
        if(id != null) {
            System.out.println("2");
            User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("유저 정보가 없습니다."));

            // Jut 토큰 서명을 통해서 서명이 정상이면 Authentication 객체를 만들어준다.
            PrincipalDetails principalDetails = new PrincipalDetails(user);
            System.out.println("principalDetails.getAuthorities() = " + principalDetails.getAuthorities());
            Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());

            // 강제로 시큐리티 세션에 접근하여 Authentication 객체를 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);

            chain.doFilter(request, response);
        }
    }
}
