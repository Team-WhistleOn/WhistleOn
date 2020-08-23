package com.halaguys.whistleon.domain.match;

import com.halaguys.whistleon.domain.team.Team;
import com.halaguys.whistleon.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="\"match\"")
public class Match {

    @Id @GeneratedValue
    @Column(name = "match_id")
    private int matchId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="home")
    private Team home;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="away")
    private Team away;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mvp")
    private User mvp;

    @Column(name="date")
    private LocalDateTime date;

    @Column(name="location")
    private String location;

    @Column(name="stadium")
    private String stadium;

    @Column(name="status")
    private String status;

    @Column(name="home_score")
    private int homeScore;

    @Column(name="away_score")
    private int awayScore;

    @Builder
    public Match(Team home, Team away, User mvp,
                 LocalDateTime date, String location, String stadium,
                 String status, int homeScore, int awayScore) {
        this.home = home;
        this.away = away;
        this.mvp = mvp;
        this.date = date;
        this.location = location;
        this.stadium = stadium;
        this.status = status;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

    public void setVersus(Team away){
        this.away=away;
    }

    public void afterMatch(String status,int homeScore, int awayScore){
        this.status=status;
        this.homeScore=homeScore;
        this.awayScore=awayScore;
    }

}
