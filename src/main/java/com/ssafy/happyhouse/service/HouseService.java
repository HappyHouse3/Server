package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.dto.house.*;
import com.ssafy.happyhouse.entity.house.Dong;
import com.ssafy.happyhouse.entity.house.HouseDeal;
import com.ssafy.happyhouse.entity.house.HouseInfo;
import com.ssafy.happyhouse.repository.HouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class HouseService {

    private final HouseRepository houseRepository;

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
                        hi.getDong().getGugunName() + hi.getDongName() + " " + hi.getAptName() + "아파트",
                        hi.getDong().getSidoName() + " " + hi.getDong().getGugunName() + " " + hi.getDongName() + " " + hi.getRoadName() + " " + String.valueOf(Long.parseLong(hi.getRoadNameBonbun()))))
                .collect(Collectors.toList());
    }

    public List<HouseDealDto> getHouseDeal(Long aptcode) throws Exception {
        List<HouseDeal> result = houseRepository.getHouseDeal(aptcode);
        return result.stream()
                .map(hd -> new HouseDealDto(hd.getId(), hd.getHouseInfo().getAptName(), hd.getDealAmount().trim(), hd.getDealYear(), hd.getDealMonth(),
                        hd.getDealDay(), String.valueOf(Math.round(Float.parseFloat(hd.getArea()))), hd.getFloor()))
                .collect(Collectors.toList());
    }
}
