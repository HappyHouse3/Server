package com.ssafy.happyhouse.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.happyhouse.dto.house.HouseDealDto;
import com.ssafy.happyhouse.dto.house.HouseInfoDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WatchListDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private HouseInfoDto houseInfo;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<HouseDealDto> houseDealList;


    @JsonProperty(access =  JsonProperty.Access.WRITE_ONLY)
    private Long aptCode;
}
