package com.ssafy.happyhouse.repository.dto;

import com.ssafy.happyhouse.entity.Reply;
import lombok.Data;

import java.util.List;

@Data
public class BoardDto {
    private Integer id;
    private String title;
    private String content;
    private String userId;
    private String password;
    List<Reply> replyList;
}
