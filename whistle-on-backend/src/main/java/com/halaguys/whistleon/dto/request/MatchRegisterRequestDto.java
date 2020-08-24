package com.halaguys.whistleon.dto.request;

import com.halaguys.whistleon.domain.team.Team;
import com.halaguys.whistleon.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MatchRegisterRequestDto {
    private int matchId;
    private int home;
    private int away;
    private LocalDateTime date;
    private String location;
    private String stadium;
    private String status;

    @Builder
    public MatchRegisterRequestDto(int matchId, int home, int away,
                                   LocalDateTime date, String location, String stadium, String status) {
        this.matchId = matchId;
        this.home = home;
        this.away=away;
        this.date = date;
        this.location = location;
        this.stadium = stadium;
        this.status = status;
    }
}
