package com.ssafy.happyhouse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class BoardInputDto {
    private String title;
    private String content;
    private Integer userNo;
    private String userId;
    private String password;
    private LocalDate regTime;
}
