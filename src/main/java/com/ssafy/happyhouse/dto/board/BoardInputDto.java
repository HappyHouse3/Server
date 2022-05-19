package com.ssafy.happyhouse.dto.board;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class BoardInputDto {
    private String title;
    private String content;
    private Integer userNo;
}
