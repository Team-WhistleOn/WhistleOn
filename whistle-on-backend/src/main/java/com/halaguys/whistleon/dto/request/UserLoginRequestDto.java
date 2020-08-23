package com.halaguys.whistleon.dto.request;


import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public class UserLoginRequestDto {
    private String email;
    private String password;
}

