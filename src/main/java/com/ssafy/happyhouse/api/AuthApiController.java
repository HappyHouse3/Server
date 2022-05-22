package com.ssafy.happyhouse.api;


import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class AuthApiController {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
}
