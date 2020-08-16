package com.halaguys.whistleon.domain.qna;

import com.halaguys.whistleon.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "qna")
public class Qna {

    @Id @GeneratedValue
    @Column(name = "qna_id")
    private int qnaId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "regdate")
    private LocalDateTime regdate;

    @OneToMany(mappedBy = "qna")
    List<QnaReply> qnaReplies = new ArrayList<>();

    @Builder
    public Qna(User user, String title, String content, LocalDateTime regdate){
        this.user = user;
        this.title = title;
        this.content = content;
        this.regdate = regdate;
    }
}
