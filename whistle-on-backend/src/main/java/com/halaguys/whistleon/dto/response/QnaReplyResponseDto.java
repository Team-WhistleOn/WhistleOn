package com.halaguys.whistleon.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class QnaReplyResponseDto {
    private String userName;
    private int qnaReplyId;
    private String content;
    private String regdate;

    @Builder
    public QnaReplyResponseDto(String userName, int qnaReplyId, String content, String regdate){
        this.userName = userName;
        this.qnaReplyId = qnaReplyId;
        this.content = content;
        this.regdate = regdate;
    }
}
