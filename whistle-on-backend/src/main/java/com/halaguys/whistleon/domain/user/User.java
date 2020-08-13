package com.halaguys.whistleon.domain.user;

import com.halaguys.whistleon.domain.team.Team;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
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
    private int win;

    @Column(name = "draw")
    private int draw;

    @Column(name = "lose")
    private int lose;

    @Column(name = "manner")
    private Double manner;

    @Column(name = "mvp_count")
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


}
