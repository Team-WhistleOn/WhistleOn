package com.halaguys.whistleon.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class QnaInfoResponseDto {
    private int qnaId;
    private String userName;
    private int userId;
    private String regdate;
    private String title;
    private String content;
    private List<QnaReplyResponseDto> qnaReply = new ArrayList<>();

    @Builder
    public QnaInfoResponseDto(int qnaId, String userName, int userId, String regdate, String title,
                              String content, List<QnaReplyResponseDto> qnaReply){
        this.qnaId = qnaId;
        this.userName = userName;
        this.userId = userId;
        this.regdate = regdate;
        this.title = title;
        this.content = content;
        this.qnaReply = qnaReply;
    }
}
