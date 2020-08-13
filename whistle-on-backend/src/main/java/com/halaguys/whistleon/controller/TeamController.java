package com.halaguys.whistleon.controller;

import com.halaguys.whistleon.dto.request.TeamRegisterRequestDto;
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

        int cnt = teamService.register(teamRegisterRequestDto);

        if(cnt!=0){
            map.put("msg","팀 등록 성공.");
            response = new ResponseEntity(map, HttpStatus.OK);
        }else{
            map.put("msg","팀 등록 실패.");
            response = new ResponseEntity(map, HttpStatus.BAD_REQUEST);
        }

        return response;
    }

}
