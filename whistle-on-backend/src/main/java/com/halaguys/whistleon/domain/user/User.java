package com.halaguys.whistleon.domain.user;

import com.halaguys.whistleon.domain.qna.Qna;
import com.halaguys.whistleon.domain.team.Team;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@DynamicInsert
@Getter
@NoArgsConstructor
@Entity
public class User {
    @Id @GeneratedValue
    @Column(name = "user_id")
    private int userId;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "location")
    private String location;

    @Column(name = "win")
    @ColumnDefault(value = "0")
    private int win;

    @Column(name = "draw")
    @ColumnDefault(value = "0")
    private int draw;

    @Column(name = "lose")
    @ColumnDefault(value = "0")
    private int lose;

    @Column(name = "manner")
    @ColumnDefault(value = "0")
    private Double manner;

    @Column(name = "mvp_count")
    @ColumnDefault(value = "0")
    private int mvpCount;

    /**
     * 팀장인지 팀원인지
     */
    @Column(name = "type")
    private String type;

    @Column(name = "join_date")
    private LocalDate joinDate;

    @Column(name = "withdrawl_date")
    private LocalDate withdrawlDate;

    @Column(name = "age")
    private int age;

    @Column(name = "height")
    private int height;

    @Column(name = "position")
    private String position;

    @OneToMany(mappedBy = "user")
    List<Qna> qnaList = new ArrayList<>();

    @Builder
    public User(String userName,String email, String location, String password, int age, int height,
                String position){
        this.userName = userName;
        this.email = email;
        this.location = location;
        this.password = password;
        this.age = age;
        this.height = height;
        this.position = position;
    }

}
