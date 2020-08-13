package com.halaguys.whistleon.controller;

import com.halaguys.whistleon.domain.user.User;
import com.halaguys.whistleon.dto.request.UserLoginRequestDto;
import com.halaguys.whistleon.dto.request.UserRegistRequestDto;
import com.halaguys.whistleon.exception.UnauthorizedException;
import com.halaguys.whistleon.service.JwtService;
import com.halaguys.whistleon.service.UserService;
import com.sun.deploy.net.HttpResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
@CrossOrigin(origins = "*")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    JwtService jwtService;

    @ApiOperation(value = "로그인")
    @PostMapping("/users/login")
    public ResponseEntity<?> login(@RequestBody UserLoginRequestDto userLoginRequestDto, HttpServletResponse response){
        Map<String,String> map = new HashMap<>();
        try{
            User user = userService.login(userLoginRequestDto);
            String token = jwtService.create(user);
            response.setHeader("Authorization",token);
            map.put("msg","로그인 성공");
            map.put("userName",user.getUserName());
            return new ResponseEntity<>(map,HttpStatus.OK);
        }catch (NoSuchElementException | UnauthorizedException e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "회원가입")
    @PostMapping("/users/signup")
    public ResponseEntity<String> signUp(@RequestBody UserRegistRequestDto userRegistRequestDto){
        
        return null;
    }
}
