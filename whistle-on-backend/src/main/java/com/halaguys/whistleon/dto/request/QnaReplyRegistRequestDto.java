package com.halaguys.whistleon.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class QnaReplyRegistRequestDto {
    int qnaId;
    String content;
}
