package com.ssafy.happyhouse.api;

import com.ssafy.happyhouse.dto.user.UserDto;
import com.ssafy.happyhouse.jwt.PrincipalDetails;
import com.ssafy.happyhouse.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserApiController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> join(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.join(userDto), HttpStatus.OK);
    }

    @GetMapping("/api/user")
    public String user(Authentication authentication) {
        System.out.println("authentication = " + ((PrincipalDetails)authentication.getPrincipal()).getAuthorities());
        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        return String.valueOf(principal.getUser().getId());
    }
}
