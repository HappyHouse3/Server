package com.ssafy.happyhouse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class SidoGugunCodeDto {
    private String sidoCode;
    private String sidoName;
    private String gugunCode;
    private String gugunName;
}
