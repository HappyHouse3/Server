package com.ssafy.happyhouse.api;

import com.ssafy.happyhouse.dto.house.HouseInfoDto;
import com.ssafy.happyhouse.dto.house.SidoDto;
import com.ssafy.happyhouse.dto.user.TokenDto;
import com.ssafy.happyhouse.dto.user.UserDto;
import com.ssafy.happyhouse.dto.user.UserUpdateDto;
import com.ssafy.happyhouse.dto.user.WatchListDto;
import com.ssafy.happyhouse.entity.WatchList;
import com.ssafy.happyhouse.jwt.PrincipalDetails;
import com.ssafy.happyhouse.repository.HouseRepository;
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
    private final HouseRepository houseRepository;

    @GetMapping("/signup")
    public ResponseEntity<List<SidoDto>> joinGetRequestDto() {
        return new ResponseEntity<>(houseRepository.findSidoList(), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> join(@RequestBody UserDto userDto) {
        log.info("회원가입 userDto:{}", userDto);
        return new ResponseEntity<>(userService.join(userDto), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserDto> user(@PathVariable Long userId) {
        return new ResponseEntity<>(userService.getUser(userId), HttpStatus.OK);
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<TokenDto> updateUser(@PathVariable Long userId, @RequestBody UserUpdateDto updateDto) {
        log.info("Input : {}", updateDto);
        return new ResponseEntity<>(userService.updateUser(userId, updateDto), HttpStatus.OK);
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Integer> deleteUser(@PathVariable Long userId) {
        return new ResponseEntity<>(userService.deleteUser(userId), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/watchlist")
    public ResponseEntity<List<WatchListDto>> getUserWatchList(@PathVariable Long userId) {
        return new ResponseEntity<>(userService.userWatchList(userId), HttpStatus.OK);
    }

    @PostMapping("/user/{userId}/watchlist")
    public ResponseEntity<String> addWatchList(@PathVariable Long userId, @RequestBody WatchListDto watchListDto) {
        userService.addWatchList(userId, watchListDto.getAptCode());
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @DeleteMapping("/user/{userId}/watchlist/{watchListId}")
    public ResponseEntity<String> removeWatchList(@PathVariable Long watchListId) {
        userService.removeWatchList(watchListId);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
