package com.ssafy.happyhouse.dto;

import com.ssafy.happyhouse.entity.house.Dong;
import com.ssafy.happyhouse.entity.house.HouseDeal;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class HouseInfoDto {
    private Integer aptCode;
    private String aptName;
    private String dongCode;
    private String dongName;
    private Integer buildYear;
    private String jibun;
    private String lat;
    private String lng;
    private String img;
    private String searchKeyword;
}
