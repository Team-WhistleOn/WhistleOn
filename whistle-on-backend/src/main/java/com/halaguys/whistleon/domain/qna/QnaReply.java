package com.halaguys.whistleon.domain.qna;

import javax.persistence.*;
import java.time.LocalDateTime;

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
}
