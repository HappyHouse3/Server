package com.ssafy.happyhouse.dto.house;

import lombok.Data;

@Data
public class DongDto {
    private String dongName;
    private String dongCode;

    public DongDto(String dongName, String dongCode) {
        this.dongName = dongName;
        this.dongCode = dongCode;
    }
}
