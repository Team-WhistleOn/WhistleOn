package com.halaguys.whistleon.domain.team;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface TeamRepository extends JpaRepository<Team,Integer> {

    Optional<Team> findByTeamName(String name);
}
