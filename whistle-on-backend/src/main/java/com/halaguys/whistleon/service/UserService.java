package com.halaguys.whistleon.service;

import com.halaguys.whistleon.domain.user.User;
import com.halaguys.whistleon.dto.request.UserLoginRequestDto;
import com.halaguys.whistleon.dto.request.UserRegistRequestDto;
import com.halaguys.whistleon.dto.response.UserInfoResponseDto;

import java.util.Optional;

public interface UserService {
    User login(UserLoginRequestDto userDto);
    Optional<User> getUserByEmail(String email);
    void matchPassword(String userPassword,String inputPassword);
    void regist(UserRegistRequestDto userDto);
    boolean checkEmail(String email);
    UserInfoResponseDto getUserInfo(String email);
}
