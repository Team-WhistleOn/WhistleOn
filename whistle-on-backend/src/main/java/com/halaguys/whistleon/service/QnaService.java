package com.halaguys.whistleon.service;

import com.halaguys.whistleon.dto.request.QnaRegistRequestDto;
import org.springframework.transaction.annotation.Transactional;

public interface QnaService {
    @Transactional
    int registQna(String email, QnaRegistRequestDto qnaRegistDto);
}
