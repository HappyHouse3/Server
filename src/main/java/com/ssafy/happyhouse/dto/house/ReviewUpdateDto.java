package com.ssafy.happyhouse.dto.house;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ReviewUpdateDto {
    @NotNull
    private String title;

    @NotNull
    private String content;

    @NotNull
    private int score;
}
