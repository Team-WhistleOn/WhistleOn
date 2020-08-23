package com.halaguys.whistleon.service;

import com.halaguys.whistleon.domain.qna.QnaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QnaServiceTest {

    @Autowired
    QnaRepository qnaRepository;


}
