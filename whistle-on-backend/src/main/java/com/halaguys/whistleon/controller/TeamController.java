package com.halaguys.whistleon.controller;

import com.halaguys.whistleon.domain.team.Team;
import com.halaguys.whistleon.dto.request.TeamModifyRequestDto;
import com.halaguys.whistleon.dto.request.TeamRegisterRequestDto;
import com.halaguys.whistleon.service.TeamService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
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
            response = new ResponseEntity(map, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

    @ApiOperation("id로 팀 한개 조회")
    @GetMapping("{id}")
    public Object findOneTeam(@PathVariable("id") Long id){
        ResponseEntity response = null;
        Map<String,Object> map = new HashMap<String,Object>();

        try {
            Team team = teamService.findTeamById(id).get();
            map.put("team",team);
            response =new ResponseEntity(map,HttpStatus.OK);
            return response;
        } catch (Exception e) {
            response= new ResponseEntity(map,HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }

    }
    @ApiOperation("name으로 팀 한개 조회")
    @GetMapping("/name/{teamName}")
    public Object findOneTeamByName(@PathVariable("teamName") String teamName){
        ResponseEntity response = null;
        Map<String,Object> map = new HashMap<String,Object>();

        try {
            Team team = teamService.findTeamByTeamName(teamName).get();
            map.put("team",team);
            response =new ResponseEntity(map,HttpStatus.OK);
            return response;
        } catch (Exception e) {
            response= new ResponseEntity(map,HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

    @ApiOperation("전체 팀 조회")
    @GetMapping("/all")
    public Object findAllTeam() {
        ResponseEntity response = null;
        Map<String, Object> map = new HashMap<String, Object>();

        try {
            List<Team> list = teamService.findAllTeam();
            map.put("list", list);
            response = new ResponseEntity(map, HttpStatus.OK);
            return response;
        } catch (Exception e) {
            response = new ResponseEntity(map, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

    @ApiOperation("팀 정보수정")
    @PutMapping("{id}")
    public Object modifyTeam(@PathVariable("id")Long id, @RequestBody TeamModifyRequestDto teamModifyRequestDto
    ,HttpServletRequest request) {
        ResponseEntity response = null;
        Map<String,Object> map = new HashMap<String,Object>();

        //권한조회필요!!!

        try {
            Team team = teamService.modifyTeam(id,teamModifyRequestDto);
            response =new ResponseEntity(map,HttpStatus.OK);
            return response;
        } catch (Exception e) {
            response= new ResponseEntity(map,HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }


    }

    @ApiOperation("팀 삭제")
    @DeleteMapping("/{id}")
    public Object deleteTeam(@PathVariable("id") Long id, HttpServletRequest request){
        ResponseEntity response = null;
        Map<String,Object> map = new HashMap<String,Object>();

        //권한조회필요!!!

        try {
            teamService.removeTeam(id);
            response =new ResponseEntity(map,HttpStatus.OK);
            return response;
        } catch (Exception e) {
            response= new ResponseEntity(map,HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }

    }

}
