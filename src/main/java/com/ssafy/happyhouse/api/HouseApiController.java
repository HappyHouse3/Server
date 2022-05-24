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

    @GetMapping("/gugun/{sidoCode}")
    public ResponseEntity<List<GugunDto>> getGugun(@PathVariable String sidoCode) throws Exception {
        return new ResponseEntity(houseService.getGugunInSido(sidoCode), HttpStatus.OK);
    }

    @GetMapping("/dong/{gugunCode}")
    public ResponseEntity<List<DongDto>> getDong(@PathVariable String gugunCode) throws Exception {
        return new ResponseEntity(houseService.getDongInGugun(gugunCode), HttpStatus.OK);
    }

    @GetMapping("/apt/{dongCode}")
    public ResponseEntity<List<HouseInfoDto>> getAptInfo(@PathVariable String dongCode) throws Exception {
        return new ResponseEntity(houseService.getAptInDong(dongCode), HttpStatus.OK);
    }

    @GetMapping("/apt/{aptCode}/deal")
    public ResponseEntity<List<HouseDealDto>> getAptDeal(@PathVariable Long aptCode) throws Exception {
        return new ResponseEntity(houseService.getHouseDeal(aptCode), HttpStatus.OK);
    }

    @GetMapping("/api/{aptCode}/review")
    public ResponseEntity<ReviewDto> getHouseReview(@PathVariable Long aptCode) throws Exception {
        return new ResponseEntity(houseService.getHouseReview(aptCode), HttpStatus.OK);
    }

    @PostMapping("/api/{aptCode}/review")
    public ResponseEntity<String> saveAptReview(@PathVariable Long aptCode, @RequestBody ReviewDto reviewDto) throws Exception {
        try {
            houseService.saveHouseReview(aptCode, reviewDto);
        } catch (Exception e) {
            return new ResponseEntity("Fail", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Success", HttpStatus.OK);
    }

    @PutMapping("/api/{aptCode}/review/{reviewId}")
    public ResponseEntity<String> updateAptReview(@PathVariable Long aptCode, @PathVariable Integer reviewId, @RequestBody ReviewUpdateDto reviewUpdateDto) throws Exception {
        try {
            houseService.updateReview(reviewId, reviewUpdateDto);
        } catch (Exception e) {
            return new ResponseEntity("Fail", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Success", HttpStatus.OK);
    }

    @DeleteMapping("/api/{aptCode}/review/{reviewId}")
    public ResponseEntity<String> deleteAptReview(@PathVariable Long aptCode, @PathVariable Integer reviewId) throws Exception {
        try {
            houseService.DeleteReview(reviewId);
        } catch (Exception e) {
            return new ResponseEntity("Fail", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity("Success", HttpStatus.OK);
    }
}
