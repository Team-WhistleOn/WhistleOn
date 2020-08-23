package com.halaguys.whistleon.domain.qna;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "qna_reply")
public class QnaReply {

    @Id @GeneratedValue
    @Column(name = "qna_reply_id")
    private int qnaReplyId;

    @ManyToOne
    @JoinColumn(name = "qna_id")
    private Qna qna;

    @Column(name = "content")
    private String content;

    @Column(name = "regdate")
    private LocalDateTime regdate;

    @Builder
    public QnaReply(String content,Qna qna,LocalDateTime regdate){
        this.content = content;
        this.qna = qna;
        this.regdate = regdate;
    }

    public void updateQnaReply(String content){
        this.content = content;
    }
}
