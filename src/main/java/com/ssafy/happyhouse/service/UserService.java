package com.ssafy.happyhouse.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ssafy.happyhouse.dto.house.HouseDealDto;
import com.ssafy.happyhouse.dto.house.HouseInfoDto;
import com.ssafy.happyhouse.dto.user.TokenDto;
import com.ssafy.happyhouse.dto.user.UserDto;
import com.ssafy.happyhouse.dto.user.UserUpdateDto;
import com.ssafy.happyhouse.dto.user.WatchListDto;
import com.ssafy.happyhouse.entity.User;
import com.ssafy.happyhouse.entity.WatchList;
import com.ssafy.happyhouse.entity.board.Sido;
import com.ssafy.happyhouse.entity.house.HouseDeal;
import com.ssafy.happyhouse.entity.house.HouseInfo;
import com.ssafy.happyhouse.repository.HouseRepository;
import com.ssafy.happyhouse.repository.UserRepository;
import com.ssafy.happyhouse.repository.WatchListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final HouseRepository houseRepository;
    private final WatchListRepository watchListRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public UserDto join(UserDto userDto) {
        if (userRepository.findByUserId(userDto.getUserId()).orElse(null) != null) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }

        Sido sido = houseRepository.findSidoById(userDto.getSidoCode());
        User user = User.builder()
                .userName(userDto.getUserId())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .nickName(userDto.getNickName())
                .email(userDto.getEmail())
                .build();
        user.setSido(sido);

        User saveUser = userRepository.save(user);

        UserDto userReturnDto = new UserDto();
        userReturnDto.setId(saveUser.getId());
        userReturnDto.setUserId(saveUser.getUserId());
        userReturnDto.setPassword(saveUser.getPassword());
        userReturnDto.setNickName(saveUser.getNickName());
        userReturnDto.setRoles(saveUser.getRoleList());
        userReturnDto.setEmail(saveUser.getEmail());
        userReturnDto.setSidoName(saveUser.getSido().getSidoName());

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
                .sidoName(user.getNickName())
                .sidoCode(user.getSido().getSidoCode())
                .build();
    }

    @Transactional
    public TokenDto updateUser(Long id, UserUpdateDto updateDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("사용자 정복가 존재하지 않습니다."));

        houseRepository.findSidoById(updateDto.getSidoCode());
        user.setPassword(passwordEncoder.encode(updateDto.getPassword()));
        user.setEmail(updateDto.getEmail());
        user.setNickName(updateDto.getNickName());
        user.setSido(houseRepository.findSidoById(updateDto.getSidoCode()));

        String token = JWT.create()
                .withSubject("cosToken")
                .withExpiresAt(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24)))
                .withClaim("userNo", user.getId())
                .withClaim("userId", user.getUserName())
                .withClaim("nickName", user.getNickName())
                .withClaim("sidoName", user.getSido().getSidoName())
                .withClaim("sidoCode", user.getSido().getSidoCode())
                .withClaim("roleType", user.getRoleList())
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

    public List<WatchListDto> userWatchList(Long id) {
        List<WatchList> result = watchListRepository.findWatchListbyUserId(id);

        return result.stream().map(w -> {
            WatchListDto watchListDto = new WatchListDto();
            watchListDto.setId(w.getId());
            HouseInfo hi = w.getHouseInfo();
            watchListDto.setHouseInfo(new HouseInfoDto(hi.getAptCode(), hi.getAptName(),
                    hi.getDong().getDongCode(), hi.getDong().getDongName(),
                    hi.getBuildYear(), hi.getJibun(), hi.getLat(), hi.getLng(),
                    hi.getDong().getGugunName() + " " + hi.getDongName() + " " + hi.getAptName() + "아파트",
                    hi.getRoadName() + " " + String.valueOf(Long.parseLong(hi.getRoadNameBonbun() == null ? "0" : hi.getRoadNameBonbun()))));
            List<HouseDealDto> houseDealDtoList = houseRepository.getHouseDeal(hi.getAptCode()).stream()
                    .map(hd -> new HouseDealDto(hd.getId(), hd.getHouseInfo().getAptName(), hd.getDealAmount().trim(), hd.getDealYear(), hd.getDealMonth(),
                            hd.getDealDay(), String.valueOf(Math.round(Float.parseFloat(hd.getArea()))), hd.getFloor()))
                    .collect(Collectors.toList());
            watchListDto.setHouseDealList(houseDealDtoList);
            return watchListDto;
        }).collect(Collectors.toList());
    }

    @Transactional
    public void addWatchList(Long userId, Long aptCode) {
        HouseInfo houseInfo = houseRepository.getApt(aptCode);
        User user = userRepository.getById(userId);

        WatchList watchList = new WatchList();
        watchList.setHouseInfo(houseInfo);
        watchList.setUser(user);

        watchListRepository.save(watchList);
    }

    @Transactional
    public void removeWatchList(Long watchListId) {
        watchListRepository.removeWatchList(watchListId);
    }
}
