package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.entity.house.HouseDeal;
import com.ssafy.happyhouse.entity.house.HouseInfo;
import com.ssafy.happyhouse.repository.dto.DongDto;
import com.ssafy.happyhouse.repository.dto.HouseDealDto;
import com.ssafy.happyhouse.repository.dto.HouseInfoDto;
import com.ssafy.happyhouse.repository.dto.SidoGugunCodeDto;
import com.ssafy.happyhouse.repository.HouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class HouseService {

    private final HouseRepository houseRepository;

    public List<SidoGugunCodeDto> getSido() throws Exception {
        return houseRepository.getSido();
    }

    public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
        return houseRepository.getGugunInSido(sido);
    }

    public List<DongDto> getDongInGugun(String gugun) throws Exception {
        return houseRepository.getDongInGugun(gugun);
    }

    public List<HouseInfoDto> getAptInDong(String dong) throws Exception {
        List<HouseInfo> houseInfos = houseRepository.getAptInDong(dong);
        return houseInfos.stream()
                .map(hi -> new HouseInfoDto(hi.getAptCode(), hi.getAptName(),
                        hi.getDong().getDongCode(), hi.getDong().getDongName(),
                        hi.getBuildYear(), hi.getJibun(), hi.getLat(), hi.getLng(), hi.getImg()))
                .collect(Collectors.toList());
    }

    public List<HouseDealDto> getHouseDeal(String aptcode) throws Exception {
        List<HouseDeal> result = houseRepository.getHouseDeal(aptcode);
        return result.stream()
                .map(hd -> new HouseDealDto(hd.getId(), hd.getHouseInfo().getAptName(), hd.getDealAmount().trim(), hd.getDealYear(), hd.getDealMonth(),
                        hd.getDealDay(), String.valueOf(Math.round(Float.parseFloat(hd.getArea()))), hd.getFloor(), hd.getType(), hd.getRentMoney()))
                .collect(Collectors.toList());
    }
}
