package com.halaguys.whistleon.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class QnaRegistRequestDto {
    private String title;
    private String content;
}
