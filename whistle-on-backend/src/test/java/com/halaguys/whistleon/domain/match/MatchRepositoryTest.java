package com.halaguys.whistleon.domain.match;

import com.halaguys.whistleon.domain.qna.Qna;
import com.halaguys.whistleon.domain.qna.QnaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MatchRepositoryTest {

    @Autowired
    MatchRepository matchRepository;

    @Test
    public void queryTest(){
        List<Match> all = matchRepository.findAllByHome_TeamId(1);
        for (Match match : all) {
            System.out.println(match.getMatchId());
        }

    }
}