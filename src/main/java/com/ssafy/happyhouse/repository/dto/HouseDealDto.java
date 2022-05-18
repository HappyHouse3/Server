package com.ssafy.happyhouse.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HouseDealDto {
    private Integer id;
    private String aptName;
    private String dealAmount;
    private Integer dealYear;
    private Integer dealMonth;
    private Integer dealDay;
    private String area;
    private String floor;
    private String type;
    private String rentMoney;
}
