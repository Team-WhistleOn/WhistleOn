package com.halaguys.whistleon.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
public class TeamRegisterRequestDto {
    private int teamId;
    private String teamName;
    private String location;
    private MultipartFile logo;
    private String description;

    @Builder
    public TeamRegisterRequestDto(int teamId, String teamName, String location, MultipartFile logo, String description) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.location = location;
        this.logo = logo;
        this.description = description;
    }
}
