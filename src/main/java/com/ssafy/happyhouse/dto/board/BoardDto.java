package com.ssafy.happyhouse.dto.board;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;

    @NotNull
    private String title;

    @NotNull
    private String content;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long userNo;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String userNickName;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String regTime;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    List<ReplyDto> replyList;
}
