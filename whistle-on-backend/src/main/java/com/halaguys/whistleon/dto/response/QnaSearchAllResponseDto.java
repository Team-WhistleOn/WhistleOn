package com.halaguys.whistleon.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class QnaSearchAllResponseDto {
    private int userId;
    private int qnaId;
    private String title;
    private String userName;
    private LocalDateTime regdate;

    @Builder
    public QnaSearchAllResponseDto(int userId, int qnaId, String title, String userName,
                                   LocalDateTime regdate){
        this.userId = userId;
        this.qnaId = qnaId;
        this.title = title;
        this.userName = userName;
        this.regdate = regdate;
    }
}
