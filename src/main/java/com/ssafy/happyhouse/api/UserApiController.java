package com.ssafy.happyhouse.api;

import com.ssafy.happyhouse.dto.user.TokenDto;
import com.ssafy.happyhouse.dto.user.UserDto;
import com.ssafy.happyhouse.dto.user.UserUpdateDto;
import com.ssafy.happyhouse.jwt.PrincipalDetails;
import com.ssafy.happyhouse.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserApiController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> join(@RequestBody UserDto userDto) {
        log.info("회원가입 userDto:{}", userDto);
        return new ResponseEntity<>(userService.join(userDto), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> user(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<TokenDto> updateUser(@PathVariable Long id, @RequestBody UserUpdateDto updateDto) {
        return new ResponseEntity<>(userService.updateUser(id, updateDto), HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Integer> deleteUser(@PathVariable Long id) {
        return new ResponseEntity<>(userService.deleteUser(id), HttpStatus.OK);
    }
}
