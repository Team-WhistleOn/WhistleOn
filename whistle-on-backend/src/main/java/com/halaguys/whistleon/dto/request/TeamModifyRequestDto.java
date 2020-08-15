package com.halaguys.whistleon.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TeamModifyRequestDto {
    private String teamName;
    private String location;
    private String logo;
    private String description;
}
