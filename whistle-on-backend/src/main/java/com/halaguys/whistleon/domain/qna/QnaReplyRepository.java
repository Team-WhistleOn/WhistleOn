package com.halaguys.whistleon.domain.qna;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QnaReplyRepository extends JpaRepository<QnaReply, Integer> {
    Optional<QnaReply> findQnaReplyByQnaReplyId(int qnaReplyId);
}
