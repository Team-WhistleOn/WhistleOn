package com.halaguys.whistleon.service;

import com.halaguys.whistleon.domain.qna.Qna;
import com.halaguys.whistleon.domain.qna.QnaRepository;
import com.halaguys.whistleon.domain.user.User;
import com.halaguys.whistleon.dto.request.QnaRegistRequestDto;
import com.halaguys.whistleon.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class QnaServiceImpl implements QnaService{

    @Autowired
    JwtService jwtService;

    @Autowired
    UserService userService;

    @Autowired
    QnaRepository qnaRepository;

    @Override
    public int registQna(String token,QnaRegistRequestDto qnaRegistDto) {
        try {
            User user = null;
            if(jwtService.isUsable(token)){
                int userId = (int) jwtService.get("userId");
                user = Optional.of(userService.getUserById(userId)
                        .orElseThrow(NoSuchElementException::new))
                        .get();
            }else{
                throw new NoSuchElementException();
            }
            Qna qna = Qna.builder()
                    .user(user)
                    .title(qnaRegistDto.getTitle())
                    .content(qnaRegistDto.getContent())
                    .regdate(LocalDateTime.now())
                    .build();
            user.getQnaList().add(qna);
            return qnaRepository.save(qna).getQnaId();
        } catch (Exception e) {
            throw new UnauthorizedException();
        }
    }
}
