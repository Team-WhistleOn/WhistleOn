package com.halaguys.whistleon.service;

import com.halaguys.whistleon.domain.match.Match;
import com.halaguys.whistleon.domain.match.MatchRepository;
import com.halaguys.whistleon.domain.team.Team;
import com.halaguys.whistleon.domain.team.TeamRepository;
import com.halaguys.whistleon.domain.user.User;
import com.halaguys.whistleon.domain.user.UserRepository;
import com.halaguys.whistleon.dto.request.MatchRegisterRequestDto;
import com.halaguys.whistleon.dto.request.MatchUpdateRequestDto;
import com.halaguys.whistleon.dto.response.MatchSearchResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private UserRepository userRepository;


    //Create
    @Override
    public void registerMatch(MatchRegisterRequestDto matchRegisterRequestDto) {
        try {
            Team home = Optional.of(teamRepository.findById(matchRegisterRequestDto.getHome()))
                    .orElseThrow(NoSuchElementException::new).get();
            Team away = Optional.of(teamRepository.findById(matchRegisterRequestDto.getAway()))
                    .orElseThrow(NoSuchElementException::new).get();

            Match match = Match.builder()
                    .home(home)
                    .away(away)
                    .date(matchRegisterRequestDto.getDate())
                    .location(matchRegisterRequestDto.getLocation())
                    .stadium(matchRegisterRequestDto.getStadium())
                    .status(matchRegisterRequestDto.getStatus())
                    .build();

            matchRepository.save(match);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Read
    @Override
    public List<MatchSearchResponseDto> getAllMatchByTeamId(int teamId) {
        List<MatchSearchResponseDto> list= null;

        try {
            List<Match> matches = new ArrayList<>();
            List<Match> homeGames = matchRepository.findAllByHome_TeamId(teamId);
            for (Match homeGame : homeGames) {
                matches.add(homeGame);
            }
            List<Match> awayGames = matchRepository.findAllByAway_TeamId(teamId);
            for (Match awayGame : awayGames) {
                matches.add(awayGame);
            }

            list=new ArrayList<>();
            for (Match match : matches) {
                MatchSearchResponseDto matchSearchResponseDto = MatchSearchResponseDto.builder()
                        .matchId(match.getMatchId())
                        .home(match.getHome())
                        .away(match.getAway())
                        .mvp(match.getMvp())
                        .date(match.getDate())
                        .location(match.getLocation())
                        .stadium(match.getStadium())
                        .status(match.getStatus())
                        .homeScore(match.getHomeScore())
                        .awayScore(match.getAwayScore())
                        .build();
                list.add(matchSearchResponseDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public MatchSearchResponseDto getMatchByMatchId(int matchId) {
        MatchSearchResponseDto matchSearchResponseDto = null;
        try {

            Match match = Optional.of(matchRepository.findById(matchId))
                    .orElseThrow(NoSuchElementException::new).get();

            matchSearchResponseDto = MatchSearchResponseDto.builder()
                    .matchId(match.getMatchId())
                    .home(match.getHome())
                    .away(match.getAway())
                    .mvp(match.getMvp())
                    .date(match.getDate())
                    .location(match.getLocation())
                    .stadium(match.getStadium())
                    .status(match.getStatus())
                    .homeScore(match.getHomeScore())
                    .awayScore(match.getAwayScore())
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return matchSearchResponseDto;
    }

    //Update
    @Override
    public void update(MatchUpdateRequestDto matchUpdateRequestDto) {
        try {
            Match match = Optional.of(matchRepository.findById(matchUpdateRequestDto.getMatchId()))
                    .orElseThrow(NoSuchElementException::new).get();

            User user = Optional.of(userRepository.findById(matchUpdateRequestDto.getMvp()))
                    .orElseThrow(NoSuchElementException::new).get();


            match.afterMatch(matchUpdateRequestDto.getStatus(),user,
                    matchUpdateRequestDto.getHomeScore(),matchUpdateRequestDto.getAwayScore());

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    //Delete
    @Override
    public void removeMatch(int matchId) {
        try{
            Match match = Optional.of(matchRepository.findById(matchId))
                    .orElseThrow(NoSuchElementException::new).get();
            matchRepository.delete(match);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
