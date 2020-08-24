package com.halaguys.whistleon.service;

import com.halaguys.whistleon.dto.request.MatchRegisterRequestDto;
import com.halaguys.whistleon.dto.request.MatchUpdateRequestDto;
import com.halaguys.whistleon.dto.response.MatchSearchResponseDto;

import java.util.List;

public interface MatchService {
    //Create
    public void registerMatch(MatchRegisterRequestDto matchRegisterRequestDto);


    //Read
    public List<MatchSearchResponseDto> getAllMatchByTeamId(int teamId);
    public MatchSearchResponseDto getMatchByMatchId(int matchId);


    //Update
    public void update(MatchUpdateRequestDto matchUpdateRequestDto);



    //Delete
    public void removeMatch(int matchId);

}
