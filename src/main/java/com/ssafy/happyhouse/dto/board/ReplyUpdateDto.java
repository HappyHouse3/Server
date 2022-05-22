package com.ssafy.happyhouse.dto.board;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class ReplyUpdateDto {

    @NotNull
    private String content;
}
