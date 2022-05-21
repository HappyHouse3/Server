package com.ssafy.happyhouse.dto.house;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HouseInfoDto {
    private Long aptCode;
    private String aptName;
    private String dongCode;
    private String dongName;
    private Integer buildYear;
    private String jibun;
    private String lat;
    private String lng;
    private String searchKeyword;
    private String roadAddress;
}
