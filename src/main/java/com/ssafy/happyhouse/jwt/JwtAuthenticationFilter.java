package com.ssafy.happyhouse.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.happyhouse.dto.user.LoginDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("JwtAuthenticationFilter : 로그인 시도 중");
        // 1. userId, password 받아서 정상인지 판단 -> principalDetailsService load 메서드 실행됨

        try {
            ObjectMapper om = new ObjectMapper();
            LoginDto loginDto = om.readValue(request.getInputStream(), LoginDto.class);

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getUserId(), loginDto.getPassword());

            // principaldetailsservice locadUserByUserName() 함수가 실행됨
            Authentication authenticate = authenticationManager.authenticate(authenticationToken);

            PrincipalDetails principalDetails = (PrincipalDetails) authenticate.getPrincipal();

            return authenticate;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // JWT 토큰을 만들어서 request요청한 사용자에게 JWT토큰을 response 해주면됨
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        log.info("로그인 완료");
        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();
        String token = JWT.create()
                .withSubject("cosToken")
                .withExpiresAt(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24)))
                .withClaim("userNo", principalDetails.getUser().getId())
                .withClaim("userId", principalDetails.getUser().getUserName())
                .withClaim("nickName", principalDetails.getUser().getNickName())
                .withClaim("sidoName", principalDetails.getUser().getSidoName())
                .sign(Algorithm.HMAC512("cos"));

        response.setContentType("application/json; charset=utf-8");
        response.addHeader("Authorization", "Bearer " + token);
        response.getWriter().write("{\"token\":\"" + token + "\", \"Authorization\": \"Bearer " + token + "\"}" );
    }




}
