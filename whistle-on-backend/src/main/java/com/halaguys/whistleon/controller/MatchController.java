package com.halaguys.whistleon.controller;

import com.halaguys.whistleon.dto.request.MatchRegisterRequestDto;
import com.halaguys.whistleon.dto.request.MatchUpdateRequestDto;
import com.halaguys.whistleon.dto.response.MatchSearchResponseDto;
import com.halaguys.whistleon.service.MatchService;
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
public class MatchController {

    @Autowired
    private MatchService matchService;

    @ApiOperation("매치 등록")
    @PostMapping("")
    public ResponseEntity<?> registerMatch(MatchRegisterRequestDto matchRegisterRequestDto){
        Map<String,Object> map = new HashMap<String,Object>();

        try {
            matchService.registerMatch(matchRegisterRequestDto);
            map.put("msg","매치 등록을 성공하였습니다.");
            return new ResponseEntity<>(map, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation("teamId로 팀 매치 조회")
    @GetMapping("/team/{teamId}")
    public ResponseEntity<? extends List<MatchSearchResponseDto>> findMatchByTeamId(@PathVariable("teamId") int teamId){
        try {
            List<MatchSearchResponseDto> list = matchService.getAllMatchByTeamId(teamId);
            return new ResponseEntity<>(list,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation("matchId으로 한개 조회")
    @GetMapping("/{matchId}")
    public ResponseEntity<? extends MatchSearchResponseDto> findOneMatchById(@PathVariable("matchId") int matchId){
        try {
            MatchSearchResponseDto match = matchService.getMatchByMatchId(matchId);
            return new ResponseEntity<>(match,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation("게임후 match 정보수정")
    @PutMapping("")
    public ResponseEntity<?> modifyMatch(@RequestBody MatchUpdateRequestDto matchUpdateRequestDto
            , HttpServletRequest request) {
        Map<String,Object> map = new HashMap<String,Object>();

        //권한조회필요!!!
        Long userId = Long.parseLong(request.getHeader("Authorization"));

        try {
            matchService.update(matchUpdateRequestDto);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(map,HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @ApiOperation("match 삭제")
    @DeleteMapping("/{matchId}")
    public ResponseEntity<?> deleteMatch(@PathVariable("matchId") int matchId, HttpServletRequest request){
        Map<String,Object> map = new HashMap<String,Object>();

        //권한조회필요!!!
        Long userId = Long.parseLong(request.getHeader("Authorization"));

        try {
            matchService.removeMatch(matchId);
            map.put("msg","삭제가 완료되었습니다.");
            return new ResponseEntity(map,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
