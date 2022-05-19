package com.ssafy.happyhouse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class BoardDto {
    private Integer id;
    private String title;
    private String content;
    private String userId;
    private String password;
    private LocalDate regTime;
    List<ReplyDto> replyList;
}
