package com.halaguys.whistleon.service;

import com.halaguys.whistleon.domain.team.Team;
import com.halaguys.whistleon.domain.team.TeamRepository;
import com.halaguys.whistleon.dto.request.TeamRegisterRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class TeamServiceImpl implements TeamService {

    private TeamRepository teamRepository;
    private MultipartFileService multipartFileService;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository, MultipartFileService multipartFileService) {
        this.teamRepository = teamRepository;
        this.multipartFileService = multipartFileService;
    }

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
}
