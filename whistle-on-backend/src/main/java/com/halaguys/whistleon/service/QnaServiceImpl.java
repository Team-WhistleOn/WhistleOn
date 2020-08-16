package com.halaguys.whistleon.service;

import com.halaguys.whistleon.domain.qna.Qna;
import com.halaguys.whistleon.domain.qna.QnaRepository;
import com.halaguys.whistleon.domain.user.User;
import com.halaguys.whistleon.dto.request.QnaRegistRequestDto;
import com.halaguys.whistleon.dto.request.QnaUpdateRequestDto;
import com.halaguys.whistleon.dto.response.QnaSearchAllResponseDto;
import com.halaguys.whistleon.exception.UnauthorizedException;
import javafx.util.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
    public int registQna(String token,QnaRegistRequestDto qnaRegistDto) throws Exception {
        try{
            int userId = (int) jwtService.get("userId");
            User user = Optional.of(userService.getUserById(userId)
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
        }catch (Exception e){
            throw new Exception();
        }
    }

    @Override
    public void modifyQna(String token, int qnaId, QnaUpdateRequestDto qnaUpdateDto) throws Exception {
        int userId = (int) jwtService.get("userId");
        Qna qna = Optional.of(qnaRepository.findQnaByQnaId(qnaId)
                .orElseThrow(NoSuchElementException::new))
                .get();
        if(qna.getUser().getUserId() == userId){
            qna.update(qnaUpdateDto.getContent(), qnaUpdateDto.getTitle());
        }
    }

    @Override
    public void deleteQna(String token, int qnaId) throws Exception{
        int userId = (int) jwtService.get("userId");
        Qna qna = Optional.of(qnaRepository.findQnaByQnaId(qnaId)
                .orElseThrow(NoSuchElementException::new))
                .get();
        if(qna.getUser().getUserId() == userId){
            qnaRepository.delete(qna);
        }
    }

    @Override
    public List<QnaSearchAllResponseDto> searchAll(){
        List<Qna> qnaList = qnaRepository.findAll();
        List<QnaSearchAllResponseDto> list = new ArrayList<>();
        qnaList.forEach(qna -> list.add(QnaSearchAllResponseDto
                .builder()
                .qnaId(qna.getQnaId())
                .title(qna.getTitle())
                .userId(qna.getUser().getUserId())
                .userName(qna.getUser().getUserName())
                .regdate(qna.getRegdate())
                .build()));
        return list;
    }
}
