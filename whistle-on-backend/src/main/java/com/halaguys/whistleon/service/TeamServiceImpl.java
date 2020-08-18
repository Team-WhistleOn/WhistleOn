package com.halaguys.whistleon.service;

import com.halaguys.whistleon.domain.team.Team;
import com.halaguys.whistleon.domain.team.TeamRepository;
import com.halaguys.whistleon.dto.request.TeamModifyRequestDto;
import com.halaguys.whistleon.dto.request.TeamRegisterRequestDto;
import com.halaguys.whistleon.dto.response.TeamResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService {

    private TeamRepository teamRepository;
    private MultipartFileService multipartFileService;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository, MultipartFileService multipartFileService) {
        this.teamRepository = teamRepository;
        this.multipartFileService = multipartFileService;
    }

    //Create
    @Override
    public void registerTeam(@RequestBody TeamRegisterRequestDto teamRegisterRequestDto) {

        String logoPath = multipartFileService.uploadFile(teamRegisterRequestDto.getLogo());

        Team team = Team.builder()
                .teamName(teamRegisterRequestDto.getTeamName())
                .location(teamRegisterRequestDto.getLocation())
                .description(teamRegisterRequestDto.getDescription())
                .logo(logoPath)
                .build();

        teamRepository.save(team);
    }

    //Read
    @Override
    public Optional<Team> findTeamById(Long id) {
        return teamRepository.findById(id);
    }

    @Override
    public Optional<Team> findTeamByTeamName(String name) {
        return teamRepository.findByTeamName(name);
    }

    @Override
    public List<Team> findAllTeam() {
        return teamRepository.findAll();
    }

    @Override
    public TeamResponseDto getTeamById(Long id) {
        Team team= Optional.of(findTeamById(id))
                .orElseThrow(NoSuchElementException::new).get();

        return TeamResponseDto.builder()
                .teamId(team.getTeamId())
                .teamName(team.getTeamName())
                .logo(team.getLogo())
                .description(team.getDescription())
                .location(team.getLocation())
                .users(team.getUsers()).build();
    }

    @Override
    public TeamResponseDto getTeamByTeamName(String name) {
        Team team= Optional.of(findTeamByTeamName(name))
                .orElseThrow(NoSuchElementException::new).get();

        return TeamResponseDto.builder()
                .teamId(team.getTeamId())
                .teamName(team.getTeamName())
                .logo(team.getLogo())
                .description(team.getDescription())
                .location(team.getLocation())
                .users(team.getUsers()).build();
    }

    @Override
    public List<TeamResponseDto> getAllTeam(){
        List<TeamResponseDto> teams = new ArrayList<>();

        List<Team> entites = findAllTeam();
        for(Team t: entites){
            teams.add(TeamResponseDto.builder()
                    .teamId(t.getTeamId())
                    .teamName(t.getTeamName())
                    .location(t.getLocation())
                    .description(t.getDescription())
                    .logo(t.getLogo())
                    .users(t.getUsers())
                    .build()
            );
        }
        return teams;
    }


    //Update
    @Override
    public void modifyTeam(Long id, TeamModifyRequestDto teamModifyRequestDto) {
        Team team = Optional.of(findTeamById(id)
        .orElseThrow(NoSuchElementException::new))
                .get();
        team.update(id,teamModifyRequestDto);
    }

    //Delete
    @Override
    public void removeTeam(Long id) {
        teamRepository.deleteById(id);
    }





}
