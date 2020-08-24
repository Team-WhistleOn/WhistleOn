package com.halaguys.whistleon.domain.match;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match,Integer> {
    List<Match> findAllByHome_TeamId(int teamId);
    List<Match> findAllByAway_TeamId(int teamId);
}
