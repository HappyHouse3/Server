package com.ssafy.happyhouse.dto.board;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticeDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;

    @NotNull
    private String title;

    @NotNull
    private String content;

    @NotNull
    private Long userNo;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String userNickName;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String regTime;
}
