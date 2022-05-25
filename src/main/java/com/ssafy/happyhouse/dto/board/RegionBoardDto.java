package com.ssafy.happyhouse.dto.board;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

public class RegionBoardDto extends BoardDto {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String sidoCode;
    private String sidoName;
}
