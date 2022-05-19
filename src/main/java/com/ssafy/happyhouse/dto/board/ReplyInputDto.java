package com.ssafy.happyhouse.dto.board;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReplyInputDto {
    private String title;
    private String content;
    private Integer userNo;
}
