package com.halaguys.whistleon.controller;

import com.halaguys.whistleon.dto.request.TeamModifyRequestDto;
import com.halaguys.whistleon.dto.request.TeamRegisterRequestDto;
import com.halaguys.whistleon.dto.response.TeamSearchResponseDto;
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

    @Autowired
    private TeamService teamService;



    @ApiOperation("팀 등록")
    @PostMapping("")
    public ResponseEntity<?> registerTeam(@RequestBody TeamRegisterRequestDto teamRegisterRequestDto){
        Map<String,Object> map = new HashMap<String,Object>();

        try {
            teamService.registerTeam(teamRegisterRequestDto);
            map.put("msg","팀 등록을 성공하였습니다.");
            return new ResponseEntity<>(map,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation("id로 팀 한개 조회")
    @GetMapping("{id}")
    public ResponseEntity<? extends TeamSearchResponseDto> findOneTeam(@PathVariable("id") int id){
        try {
            TeamSearchResponseDto team = teamService.getTeamById(id);
            return new ResponseEntity<>(team,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @ApiOperation("name으로 팀 한개 조회")
    @GetMapping("/name/{teamName}")
    public ResponseEntity<? extends TeamSearchResponseDto> findOneTeamByName(@PathVariable("teamName") String teamName){
        try {
            TeamSearchResponseDto team = teamService.getTeamByTeamName(teamName);
            return new ResponseEntity<>(team,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation("전체 팀 조회")
    @GetMapping("/all")
    public ResponseEntity<? extends List<TeamSearchResponseDto>> findAllTeam() {
        try {
            List<TeamSearchResponseDto> list = teamService.getAllTeam();
            return new ResponseEntity<>(list,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation("팀 정보수정")
    @PutMapping("{id}")
    public ResponseEntity<?> modifyTeam(@PathVariable("id")int id, @RequestBody TeamModifyRequestDto teamModifyRequestDto
    ,HttpServletRequest request) {
        Map<String,Object> map = new HashMap<String,Object>();

        //권한조회필요!!!
        Long userId = Long.parseLong(request.getHeader("Authorization"));

        try {
            teamService.modifyTeam(id,teamModifyRequestDto);
            map.put("msg","팀 정보 수정을 성공하였습니다.");
            return new ResponseEntity(map,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @ApiOperation("팀 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTeam(@PathVariable("id") int id, HttpServletRequest request){
        Map<String,Object> map = new HashMap<String,Object>();

        //권한조회필요!!!
        Long userId = Long.parseLong(request.getHeader("Authorization"));

        try {
            teamService.removeTeam(id);
            map.put("msg","삭제가 완료되었습니다.");
            return new ResponseEntity(map,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
