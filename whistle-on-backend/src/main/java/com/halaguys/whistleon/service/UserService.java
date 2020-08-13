package com.halaguys.whistleon.service;

import com.halaguys.whistleon.domain.user.User;
import com.halaguys.whistleon.dto.request.UserLoginRequestDto;

public interface UserService {
    User login(UserLoginRequestDto userLoginRequestDto);
    User getUserByEmail(String email);
    void matchPassword(String userPassword,String inputPassword);
}
