package com.halaguys.whistleon.dto.request;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
public class TeamRegisterRequestDto {
    private int teamId;
    private String teamName;
    private String location;
    private MultipartFile logo;
    private String description;
}
