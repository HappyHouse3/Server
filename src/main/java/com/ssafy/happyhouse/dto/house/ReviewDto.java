package com.ssafy.happyhouse.dto.house;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ReviewDto {

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long userNo;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String userNickName;

    @NotNull
    private String title;

    @NotNull
    private String content;

    @NotNull
    private int score;
}
