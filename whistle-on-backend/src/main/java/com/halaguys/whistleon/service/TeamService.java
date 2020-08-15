package com.halaguys.whistleon.service;

import com.halaguys.whistleon.domain.team.Team;
import com.halaguys.whistleon.dto.request.TeamModifyRequestDto;
import com.halaguys.whistleon.dto.request.TeamRegisterRequestDto;

import java.util.List;
import java.util.Optional;

public interface TeamService {
    //Create
    public Team registerTeam(TeamRegisterRequestDto teamRegisterRequestDto);

    //Read
    public Optional<Team> findTeamById(Long id);
    public Optional<Team> findTeamByTeamName(String name);
    public List<Team> findAllTeam();

    //Update
    public Team modifyTeam(Long id, TeamModifyRequestDto teamModifyRequestDto);

    //Delete
    public void removeTeam(Long id);



}
