package com.halaguys.whistleon.service;

import com.halaguys.whistleon.domain.user.User;

public interface UserService {
    User getUserByEmail(String email);
    void matchPassword(String userPassword,String inputPassword);
}
