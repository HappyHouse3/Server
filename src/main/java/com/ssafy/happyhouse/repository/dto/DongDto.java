package com.ssafy.happyhouse.repository.dto;

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
