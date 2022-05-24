package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.dto.house.*;
import com.ssafy.happyhouse.entity.User;
import com.ssafy.happyhouse.entity.house.Dong;
import com.ssafy.happyhouse.entity.house.HouseDeal;
import com.ssafy.happyhouse.entity.house.HouseInfo;
import com.ssafy.happyhouse.entity.house.Review;
import com.ssafy.happyhouse.repository.HouseRepository;
import com.ssafy.happyhouse.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class HouseService {

    private final HouseRepository houseRepository;
    private final UserRepository userRepository;

    public List<SidoDto> getSido() throws Exception {
        return houseRepository.getSido();
    }

    public List<GugunDto> getGugunInSido(String sidoCode) throws Exception {
        return houseRepository.getGugunInSido(sidoCode);
    }

    public List<DongDto> getDongInGugun(String gugunCode) throws Exception {
        return houseRepository.getDongInGugun(gugunCode)
                .stream().map(d -> new DongDto(d.getDongCode(), d.getDongName()))
                .collect(Collectors.toList());
    }

    public List<HouseInfoDto> getAptInDong(String dong) throws Exception {
        List<HouseInfo> houseInfos = houseRepository.getAptInDong(dong);
        return houseInfos.stream()
                .map(hi -> new HouseInfoDto(hi.getAptCode(), hi.getAptName(),
                        hi.getDong().getDongCode(), hi.getDong().getDongName(),
                        hi.getBuildYear(), hi.getJibun(), hi.getLat(), hi.getLng(),
                        hi.getDong().getGugunName() + " " + hi.getDongName() + " " + hi.getAptName() + "아파트",
                        hi.getRoadName() + " " + String.valueOf(Long.parseLong(hi.getRoadNameBonbun() == null ? "0" : hi.getRoadNameBonbun()))))
                .collect(Collectors.toList());
    }

    public List<HouseDealDto> getHouseDeal(Long aptcode) throws Exception {
        List<HouseDeal> result = houseRepository.getHouseDeal(aptcode);
        return result.stream()
                .map(hd -> new HouseDealDto(hd.getId(), hd.getHouseInfo().getAptName(), hd.getDealAmount().trim(), hd.getDealYear(), hd.getDealMonth(),
                        hd.getDealDay(), String.valueOf(Math.round(Float.parseFloat(hd.getArea()))), hd.getFloor()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void saveHouseReview(Long aptCode, ReviewDto reviewDto) {
        HouseInfo houseInfo = houseRepository.getApt(aptCode);
        User user = userRepository.getById(reviewDto.getUserNo());

        Review review = new Review();
        review.setHouseInfo(houseInfo);
        review.setUser(user);
        review.setTitle(reviewDto.getTitle());
        review.setContent(reviewDto.getContent());
        review.setScore(reviewDto.getScore());

        houseRepository.saveReview(review);
    }


    public List<ReviewDto> getHouseReview(Long aptcode) {
        List<Review> result = houseRepository.getHouseReview(aptcode);
        return result.stream().map((r) -> {
            ReviewDto reviewDto = new ReviewDto();
            reviewDto.setReviewId(r.getId());
            reviewDto.setTitle(r.getTitle());
            reviewDto.setUserNickName(r.getUser().getNickName());
            reviewDto.setContent(r.getContent());
            reviewDto.setScore(r.getScore());
            return reviewDto;
        }).collect(Collectors.toList());
    }

    @Transactional
    public void updateReview(Integer reviewId, ReviewUpdateDto reviewUpdateDto) {
        Review review = houseRepository.findReviewById(reviewId);
        review.setTitle(reviewUpdateDto.getTitle());
        review.setContent(reviewUpdateDto.getContent());
        review.setScore(reviewUpdateDto.getScore());
    }

    @Transactional
    public void DeleteReview(Integer reviewId) {
        Review review = houseRepository.findReviewById(reviewId);
        System.out.println("리뷰 찾기 성공");
        houseRepository.deleteReview(review);
        System.out.println("리뷰 삭제 성공");
    }
}
