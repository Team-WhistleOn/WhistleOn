package com.halaguys.whistleon.service;

import com.halaguys.whistleon.domain.user.User;

public interface JwtService {
    String create(User user);
    boolean isUsable(String jwt) throws Exception;
    Object get(String key) throws Exception;
}
