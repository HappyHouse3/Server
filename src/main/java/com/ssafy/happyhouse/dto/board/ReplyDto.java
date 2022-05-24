package com.ssafy.happyhouse.dto.board;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReplyDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;

    @NotNull
    private String content;

    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long userNo;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String userNickName;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime regTime;
}
