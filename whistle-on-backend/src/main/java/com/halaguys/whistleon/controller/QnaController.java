package com.halaguys.whistleon.controller;

import com.halaguys.whistleon.dto.request.QnaRegistRequestDto;
import com.halaguys.whistleon.exception.UnauthorizedException;
import com.halaguys.whistleon.service.QnaService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@CrossOrigin(origins = "*")
@RestController
public class QnaController {

    @Autowired
    QnaService qnaService;

    @ApiOperation("qna 등록")
    @PostMapping("/qna")
    public ResponseEntity<?> registQna(HttpServletRequest request, @RequestBody QnaRegistRequestDto qnaRegistRequestDto){
        Map<String, Integer> map = new HashMap<>();
        try{
            int qnaId = qnaService.registQna(request.getHeader("Authorization"),qnaRegistRequestDto);
            map.put("qnaId",qnaId);
            return new ResponseEntity<>(map,HttpStatus.CREATED);
        }catch (NoSuchElementException | UnauthorizedException e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation("qna 삭제")
    @DeleteMapping("/qna/{qnaId}")
    public ResponseEntity<?> deleteQna(HttpServletRequest request, @PathVariable int qnaId){
        
    }
}
