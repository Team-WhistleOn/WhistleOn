package com.halaguys.whistleon.service;

import com.halaguys.whistleon.domain.qna.Qna;
import com.halaguys.whistleon.domain.qna.QnaRepository;
import com.halaguys.whistleon.domain.user.User;
import com.halaguys.whistleon.dto.request.QnaRegistRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class QnaServiceImpl implements QnaService{

    @Autowired
    UserService userService;

    @Autowired
    QnaRepository qnaRepository;

    @Override
    public int registQna(String email, QnaRegistRequestDto qnaRegistDto) {
        User user = Optional.of(userService.getUserByEmail(email)
                .orElseThrow(NoSuchElementException::new))
                .get();
        Qna qna = Qna.builder()
                .user(user)
                .title(qnaRegistDto.getTitle())
                .content(qnaRegistDto.getContent())
                .regdate(LocalDateTime.now())
                .build();
        user.getQnaList().add(qna);
        return qnaRepository.save(qna).getQnaId();
    }
}
