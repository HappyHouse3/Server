package com.ssafy.happyhouse.api;

import com.ssafy.happyhouse.dto.house.*;
import com.ssafy.happyhouse.service.HouseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequestMapping("/api/map")
@Slf4j
@RequiredArgsConstructor
@Controller
public class HouseApiController {

    private final HouseService houseService;

    @GetMapping("/sido")
    public ResponseEntity<List<SidoDto>> findSido() throws Exception {
        return new ResponseEntity(houseService.getSido(), HttpStatus.OK);
    }

    @GetMapping("/gugun/{sidoName}")
    public ResponseEntity<List<gugunDto>> getGugun(@PathVariable String sidoName) throws Exception {
        return new ResponseEntity(houseService.getGugunInSido(sidoName), HttpStatus.OK);
    }

    @GetMapping("/dong/{gugunName}")
    public ResponseEntity<List<DongDto>> getDong(@PathVariable String gugunName) throws Exception {
        return new ResponseEntity(houseService.getDongInGugun(gugunName), HttpStatus.OK);
    }

    @GetMapping("/apt/{dongCode}")
    public ResponseEntity<List<HouseInfoDto>> getAptInfo(@PathVariable String dongCode) throws Exception {
        return new ResponseEntity(houseService.getAptInDong(dongCode), HttpStatus.OK);
    }

    @GetMapping("/apt/{aptcode}/deal")
    public ResponseEntity<List<HouseDealDto>> getAptDeal(@PathVariable String aptcode) throws Exception {
        return new ResponseEntity(houseService.getHouseDeal(aptcode), HttpStatus.OK);
    }
}
