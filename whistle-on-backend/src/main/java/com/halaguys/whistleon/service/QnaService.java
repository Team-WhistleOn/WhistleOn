package com.halaguys.whistleon.service;

import com.halaguys.whistleon.dto.request.QnaRegistRequestDto;
import com.halaguys.whistleon.dto.request.QnaUpdateRequestDto;
import org.springframework.transaction.annotation.Transactional;

public interface QnaService {
    @Transactional
    int registQna(String token, QnaRegistRequestDto qnaRegistDto) throws Exception;
    @Transactional
    void modifyQna(String token, int qnaId, QnaUpdateRequestDto qnaUpdateDto) throws Exception;
}
