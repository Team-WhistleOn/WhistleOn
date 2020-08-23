package com.halaguys.whistleon.service;

import com.halaguys.whistleon.dto.request.QnaRegistRequestDto;
import com.halaguys.whistleon.dto.request.QnaReplyRegistRequestDto;
import com.halaguys.whistleon.dto.request.QnaUpdateRequestDto;
import com.halaguys.whistleon.dto.response.QnaInfoResponseDto;
import com.halaguys.whistleon.dto.response.QnaSearchAllResponseDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface QnaService {
    @Transactional
    int registQna(String token, QnaRegistRequestDto qnaRegistDto) throws Exception;
    @Transactional
    void modifyQna(String token, int qnaId, QnaUpdateRequestDto qnaUpdateDto) throws Exception;
    @Transactional
    void deleteQna(String token, int qnaId) throws Exception;
    @Transactional
    List<QnaSearchAllResponseDto> searchAll() throws Exception;
    @Transactional
    List<QnaSearchAllResponseDto> searchQnaByPage(int page);
    @Transactional
    QnaInfoResponseDto getQnaInfo(int qnaId);
    @Transactional
    int insertQnaReply(QnaReplyRegistRequestDto qnaReplyRegistDto) throws Exception;
    @Transactional
    void deleteQnaReply(int qnaId) throws Exception;
    @Transactional
    void updateQnaReply(int qnaId, String content) throws Exception;
}
