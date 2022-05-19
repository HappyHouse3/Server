package com.ssafy.happyhouse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ReplyDto {
    private Integer id;
    private String content;
    private String userNo;
    private String userId;
    private String password;
    private LocalDateTime regTime;
}
