package com.halaguys.whistleon.controller;

import com.halaguys.whistleon.domain.team.Team;
import com.halaguys.whistleon.dto.request.TeamRegisterRequestDto;
import com.halaguys.whistleon.service.TeamService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/team")
public class TeamController {

    private TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }


    @ApiOperation("팀 등록")
    @PostMapping("")
    public Object registerTeam(@RequestBody TeamRegisterRequestDto teamRegisterRequestDto){
        ResponseEntity response = null;
        Map<String,Object> map = new HashMap<String,Object>();

        try {
            Team team = teamService.registerTeam(teamRegisterRequestDto);
            response =new ResponseEntity(map,HttpStatus.OK);
            return response;
        } catch (Exception e) {
            response= new ResponseEntity(map,HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

    @ApiOperation("팀 삭제")
    @DeleteMapping("/{teamId}")
    public Object deleteTeam(@PathVariable("teamId") Long teamId){

        return null;
    }

}
