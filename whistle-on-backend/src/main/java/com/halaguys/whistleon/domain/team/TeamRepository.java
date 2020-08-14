package com.halaguys.whistleon.domain.team;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TeamRepository extends JpaRepository<Team,Long> {

}
