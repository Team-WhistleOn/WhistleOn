package com.halaguys.whistleon.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TeamModifyRequestDto {
    private String teamName;
    private String location;
    private String logo;
    private String description;

    @Builder
    public TeamModifyRequestDto(String teamName, String location, String logo, String description) {
        this.teamName = teamName;
        this.location = location;
        this.logo = logo;
        this.description = description;
    }
}
