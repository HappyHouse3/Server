package com.ssafy.happyhouse.api;

import com.ssafy.happyhouse.dto.DongDto;
import com.ssafy.happyhouse.dto.HouseDealDto;
import com.ssafy.happyhouse.dto.HouseInfoDto;
import com.ssafy.happyhouse.dto.SidoGugunCodeDto;
import com.ssafy.happyhouse.service.HouseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RequestMapping("/api/house")
@Slf4j
@RequiredArgsConstructor
@Controller
public class HouseApiController {

    private final HouseService houseService;

    @GetMapping("/sido")
    public ResponseEntity<List<SidoGugunCodeDto>> findSido() throws Exception {
        log.debug("sido : {}", houseService.getSido());
        return new ResponseEntity(houseService.getSido(), HttpStatus.OK);
    }

    @GetMapping("/gugun/{sido}")
    public ResponseEntity<List<SidoGugunCodeDto>> getGugun(@PathVariable String sido) throws Exception {
        System.out.println(sido);
        return new ResponseEntity(houseService.getGugunInSido(sido), HttpStatus.OK);
    }

    @GetMapping("/dong/{gugun}")
    public ResponseEntity<List<DongDto>> getDong(@PathVariable String gugun) throws Exception {
        System.out.println("gugun = " + gugun);
        System.out.println(houseService.getDongInGugun(gugun));
        return new ResponseEntity(houseService.getDongInGugun(gugun), HttpStatus.OK);
    }

    @GetMapping("/apt/{dong}")
    public ResponseEntity<List<HouseInfoDto>> getAptInfo(@PathVariable String dong) throws Exception {
        return new ResponseEntity(houseService.getAptInDong(dong), HttpStatus.OK);
    }

    @GetMapping("/apt/{aptcode}/deal")
    public ResponseEntity<List<HouseDealDto>> getAptDeal(@PathVariable String aptcode) throws Exception {
        return new ResponseEntity(houseService.getHouseDeal(aptcode), HttpStatus.OK);
    }
}
