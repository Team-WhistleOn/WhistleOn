package com.halaguys.whistleon.domain.qna;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QnaRepository extends JpaRepository<Qna,Integer> {
    Optional<Qna> findQnaByQnaId(int qnaId);

}
