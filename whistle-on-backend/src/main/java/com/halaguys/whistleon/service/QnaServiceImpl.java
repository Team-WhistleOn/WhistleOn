package com.halaguys.whistleon.service;

import com.halaguys.whistleon.domain.qna.Qna;
import com.halaguys.whistleon.domain.qna.QnaReply;
import com.halaguys.whistleon.domain.qna.QnaReplyRepository;
import com.halaguys.whistleon.domain.qna.QnaRepository;
import com.halaguys.whistleon.domain.user.User;
import com.halaguys.whistleon.dto.request.QnaRegistRequestDto;
import com.halaguys.whistleon.dto.request.QnaReplyRegistRequestDto;
import com.halaguys.whistleon.dto.request.QnaUpdateRequestDto;
import com.halaguys.whistleon.dto.response.QnaInfoResponseDto;
import com.halaguys.whistleon.dto.response.QnaReplyResponseDto;
import com.halaguys.whistleon.dto.response.QnaSearchAllResponseDto;
import com.halaguys.whistleon.exception.UnauthorizedException;
import javafx.util.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @Autowired
    QnaReplyRepository qnaReplyRepository;

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

    @Override
    public List<QnaSearchAllResponseDto> searchQnaByPage(int page) {
        PageRequest pageRequest = PageRequest.of(page,10);
        Page<Qna> qnaList = qnaRepository.findAll(pageRequest);
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

    @Override
    public QnaInfoResponseDto getQnaInfo(int qnaId) {
        Qna qna = Optional.of(qnaRepository.findQnaByQnaId(qnaId)
                .orElseThrow(NoSuchElementException::new))
                .get();
        List<QnaReplyResponseDto> qnaReplyResponseDtos = new ArrayList<>();
        qna.getQnaReplies()
                .forEach(qnaReply -> qnaReplyResponseDtos.add(QnaReplyResponseDto
                 .builder()
                 .userName("관리자")
                 .regdate(DateFormatter.DateToString(qnaReply.getRegdate()))
                 .content(qnaReply.getContent())
                 .qnaReplyId(qnaReply.getQnaReplyId())
                 .build()));
        return QnaInfoResponseDto.builder()
                .qnaId(qna.getQnaId())
                .qnaReply(qnaReplyResponseDtos)
                .content(qna.getContent())
                .title(qna.getTitle())
                .regdate(DateFormatter.DateToString(qna.getRegdate()))
                .userId(qna.getUser().getUserId())
                .userName(qna.getUser().getUserName())
                .build();
    }

    @Override
    public int insertQnaReply(QnaReplyRegistRequestDto qnaReplyRegistDto) throws Exception {
        String adminEmail = (String) jwtService.get("email");
        if(adminEmail.equals("whistleon1@gmail.com")){
            Qna qna = Optional.of(qnaRepository.findQnaByQnaId(qnaReplyRegistDto.getQnaId())
                    .orElseThrow(NoSuchElementException::new))
                    .get();
            QnaReply qnaReply = QnaReply
                    .builder()
                    .qna(qna)
                    .regdate(LocalDateTime.now())
                    .content(qnaReplyRegistDto.getContent())
                    .build();
            qnaReplyRepository.save(qnaReply);
            qna.getQnaReplies().add(qnaReply);
            return qnaReply.getQnaReplyId();
        }else{
            throw new UnauthorizedException();
        }
    }

    @Override
    public void deleteQnaReply(int qnaId) throws Exception {
        String adminEmail = (String) jwtService.get("email");
        if(adminEmail.equals("whistleon1@gmail.com")){
            QnaReply qnaReply = Optional.of(qnaReplyRepository.findQnaReplyByQnaReplyId(qnaId)
                    .orElseThrow(NoSuchElementException::new))
                    .get();
            qnaReplyRepository.delete(qnaReply);
        }else{
            throw new UnauthorizedException();
        }
    }

    @Override
    public void updateQnaReply(int qnaId, String content) throws Exception {
        String adminEmail = (String) jwtService.get("email");
        if(adminEmail.equals("whistleon1@gmail.com")){
            QnaReply qnaReply = Optional.of(qnaReplyRepository.findQnaReplyByQnaReplyId(qnaId)
                    .orElseThrow(NoSuchElementException::new))
                    .get();
            qnaReply.updateQnaReply(content);
            Qna qna = qnaReply.getQna();
            for(QnaReply qnaReplyItem : qna.getQnaReplies()){
                if(qnaReplyItem.getQnaReplyId() == qnaId){
                    qnaReplyItem.updateQnaReply(content);
                }
            }
        }else{
            throw new UnauthorizedException();
        }
    }
}
