package com.halaguys.whistleon.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserInfoResponseDto {
    private String email;
    private String userName;
    private String location;
    private int height;
    private int age;
    private String teamName;

    @Builder
    public UserInfoResponseDto(String email, String userName, String location
            , int height, int age, String teamName){
        this.email = email;
        this.userName = userName;
        this.location = location;
        this.height = height;
        this.age = age;
        this.teamName = teamName;
    }
}
