package com.halaguys.whistleon.dto.response;

import com.halaguys.whistleon.domain.team.Team;
import com.halaguys.whistleon.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MatchSearchResponseDto {
    private int matchId;
    private Team home;
    private Team away;
    private User mvp;
    private LocalDateTime date;
    private String location;
    private String stadium;
    private String status;
    private int homeScore;
    private int awayScore;

    @Builder
    public MatchSearchResponseDto(int matchId, Team home, Team away, User mvp, LocalDateTime date, String location, String stadium, String status, int homeScore, int awayScore) {
        this.matchId = matchId;
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
}
