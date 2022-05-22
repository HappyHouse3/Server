package com.ssafy.happyhouse.dto.board;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class BoardDto {

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String token;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;

    @NotNull
    private String title;

    @NotNull
    private String content;

    @NotNull
    private String userId;

    @NotNull String userDetail;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime regTime;


    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    List<ReplyDto> replyList;
}
