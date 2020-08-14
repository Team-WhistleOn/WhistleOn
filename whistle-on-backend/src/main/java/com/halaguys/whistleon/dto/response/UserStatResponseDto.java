package com.halaguys.whistleon.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserStatResponseDto {
    private int win;
    private int draw;
    private int lose;
    private int age;
    private int height;
    private double manner;
    private int mvpCount;

    @Builder
    public UserStatResponseDto(int win, int draw, int lose, int age, int height, double manner, int mvpCount){
        this.win = win;
        this.draw = draw;
        this.lose = lose;
        this.age = age;
        this.height = height;
        this.manner = manner;
        this.mvpCount = mvpCount;
    }
}
