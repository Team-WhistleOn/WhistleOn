package com.halaguys.whistleon.controller;

import com.halaguys.whistleon.domain.qna.QnaReply;
import com.halaguys.whistleon.dto.request.QnaRegistRequestDto;
import com.halaguys.whistleon.dto.request.QnaReplyRegistRequestDto;
import com.halaguys.whistleon.dto.request.QnaUpdateRequestDto;
import com.halaguys.whistleon.dto.response.QnaInfoResponseDto;
import com.halaguys.whistleon.dto.response.QnaSearchAllResponseDto;
import com.halaguys.whistleon.exception.UnauthorizedException;
import com.halaguys.whistleon.service.QnaService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;
import java.util.HashMap;
import java.util.List;
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

    @ApiOperation("qna 업데이트")
    @PatchMapping("/qna/{qnaId}")
    public ResponseEntity<?> updateQna(HttpServletRequest request, @PathVariable int qnaId,
                                       @RequestBody QnaUpdateRequestDto qnaUpdateRequestDto){
        Map<String, String> map = new HashMap<>();
        try{
            qnaService.modifyQna(request.getHeader("Authorization"),qnaId,qnaUpdateRequestDto);
            map.put("msg","성공적으로 업데이트 되었습니다");
            return new ResponseEntity<>(map,HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation("qna 삭제")
    @DeleteMapping("/qna/{qnaId}")
    public ResponseEntity<?> deleteQna(HttpServletRequest request, @PathVariable int qnaId){
        Map<String, String> map = new HashMap<>();
        try{
            qnaService.deleteQna(request.getHeader("Authorization"),qnaId);
            map.put("msg","성공적으로 삭제하였습니다.");
            return new ResponseEntity<>(map,HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation("qna 전체리스트 불러오기")
    @GetMapping("/qna")
    public ResponseEntity<?> searchAll() {
        try{
            List<QnaSearchAllResponseDto> qnaSearchAllResponseDtoList = qnaService.searchAll();
            return new ResponseEntity<>(qnaSearchAllResponseDtoList, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation("qna 페이징을 통한 리스트 불러오기")
    @GetMapping("/qna/{page}")
    public ResponseEntity<?> searchList(@PathVariable int page){
        try{
            List<QnaSearchAllResponseDto> qnaSearchAllResponseDtoList = qnaService.searchQnaByPage(page-1);
            return new ResponseEntity<>(qnaSearchAllResponseDtoList, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation("qna 한개 정보 가져오기(reply도)")
    @GetMapping("/qna/info/{qnaId}")
    public ResponseEntity<? extends QnaInfoResponseDto> getQnaInfo(@PathVariable int qnaId){
        try{
            QnaInfoResponseDto qnaInfoResponseDto = qnaService.getQnaInfo(qnaId);
            return new ResponseEntity<>(qnaInfoResponseDto,HttpStatus.OK);
        }catch (NoSuchElementException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation("qna reply 등록")
    @PostMapping("/qna/reply")
    public ResponseEntity<?> registQnaReply(@RequestBody QnaReplyRegistRequestDto qnaReplyRegistRequestDto){
        try {
            int qnaReplyId = qnaService.insertQnaReply(qnaReplyRegistRequestDto);
            Map<String, Integer> map = new HashMap<>();
            map.put("qnaReplyId",qnaReplyId);
            return new ResponseEntity<>(map,HttpStatus.CREATED);
        } catch (UnauthorizedException e) {
            // 관리자 계정으로 로그인한 경우가 아닌 경우.
            //프론트에서 한번 체크하지만, 백에서도 체크 한번 더해주면 좋음.
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (NoSuchElementException e){
            // 프론트에서 보낸 qnaId가 존재하지 않는 경우.
            // 해당 리소스가 없는 경우이므로 404를 주는 것이 좋다.
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation("qna reply 삭제")
    @DeleteMapping("/qna/reply/{qnaReplyId}")
    public ResponseEntity<?> deleteQnaReply(@PathVariable int qnaReplyId){
        try {
            qnaService.deleteQnaReply(qnaReplyId);
            Map<String,String> map = new HashMap<>();
            map.put("msg","삭제를 완료하였습니다.");
            return new ResponseEntity<>(map,HttpStatus.OK);
        } catch (UnauthorizedException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (NoSuchElementException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
