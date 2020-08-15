package com.halaguys.whistleon.service;

import com.halaguys.whistleon.domain.team.Team;
import com.halaguys.whistleon.domain.team.TeamRepository;
import com.halaguys.whistleon.dto.request.TeamModifyRequestDto;
import com.halaguys.whistleon.dto.request.TeamRegisterRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
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
    public Team registerTeam(@RequestBody TeamRegisterRequestDto teamRegisterRequestDto) {

        String logoPath = multipartFileService.uploadFile(teamRegisterRequestDto.getLogo());

        Team team = Team.builder()
                .teamName(teamRegisterRequestDto.getTeamName())
                .location(teamRegisterRequestDto.getLocation())
                .description(teamRegisterRequestDto.getDescription())
                .build();

        return teamRepository.save(team);
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

    //Update
    @Override
    public Team modifyTeam(Long id, TeamModifyRequestDto teamModifyRequestDto) {
        Team team = findTeamById(id).get();
        team.update(id,teamModifyRequestDto);
        return team;
    }

    //Delete
    @Override
    public void removeTeam(Long id) {
        teamRepository.deleteById(id);
    }
}
