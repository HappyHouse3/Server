package com.ssafy.happyhouse.dto.house;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class NoticeDto {
    private Integer id;
    private String title;
    private String content;
    private String userId;
    private String password;
    private LocalDateTime regTime;
}
