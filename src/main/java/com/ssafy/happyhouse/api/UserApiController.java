package com.ssafy.happyhouse.api;

import com.ssafy.happyhouse.dto.user.UserInputDto;
import com.ssafy.happyhouse.entity.User;
import com.ssafy.happyhouse.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserApiController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> showUser(@PathVariable Integer id) {
        return new ResponseEntity<>(userService.findUser(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> showUser() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Integer> join(UserInputDto userInputDto) {
        userService.join(userInputDto);
        return new ResponseEntity<>(1, HttpStatus.OK);
    }
}
