package com.ssafy.happyhouse.repository.dto;

import com.ssafy.happyhouse.entity.Reply;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class ReplyDto {
    private Integer id;
    private String content;
    private String userId;
    private String password;
    private LocalDateTime regTime;
}
