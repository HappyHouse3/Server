package com.ssafy.happyhouse.dto.board;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BoardInputDto {
    @NotNull
    private String title;

    @NotNull
    private String content;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long userNo;
}
