package com.halaguys.whistleon.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserRegistRequestDto {
    private String email;
    private String userName;
    private String password;
    private String location;
    private String position;
    private int height;
    private int age;
}
