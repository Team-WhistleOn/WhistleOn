package com.halaguys.whistleon.service;

import com.halaguys.whistleon.domain.user.User;
import com.halaguys.whistleon.dto.request.UserLoginRequestDto;
import com.halaguys.whistleon.dto.request.UserRegistRequestDto;

public interface UserService {
    User login(UserLoginRequestDto userDto);
    User getUserByEmail(String email);
    void matchPassword(String userPassword,String inputPassword);
    void regist(UserRegistRequestDto userDto);
}
