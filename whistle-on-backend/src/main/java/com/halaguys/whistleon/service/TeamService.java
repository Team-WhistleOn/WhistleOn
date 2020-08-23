package com.halaguys.whistleon.service;

import com.halaguys.whistleon.domain.team.Team;
import com.halaguys.whistleon.dto.request.TeamModifyRequestDto;
import com.halaguys.whistleon.dto.request.TeamRegisterRequestDto;
import com.halaguys.whistleon.dto.response.TeamResponseDto;

import java.util.List;
import java.util.Optional;

public interface TeamService {
    //Create
    public void registerTeam(TeamRegisterRequestDto teamRegisterRequestDto);

    //Read
    public Optional<Team> findTeamById(int id);
    public Optional<Team> findTeamByTeamName(String name);
    public List<Team> findAllTeam();

    public TeamResponseDto getTeamById(int id);
    public TeamResponseDto getTeamByTeamName(String name);
    public List<TeamResponseDto> getAllTeam();

    //Update
    public void modifyTeam(int id, TeamModifyRequestDto teamModifyRequestDto);

    //Delete
    public void removeTeam(int id);



}
