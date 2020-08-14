package com.halaguys.whistleon.service;

import com.halaguys.whistleon.domain.team.Team;
import com.halaguys.whistleon.dto.request.TeamRegisterRequestDto;

public interface TeamService {
    public Team registerTeam(TeamRegisterRequestDto teamRegisterRequestDto);
}
