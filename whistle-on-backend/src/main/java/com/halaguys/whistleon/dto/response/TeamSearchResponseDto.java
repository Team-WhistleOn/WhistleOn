package com.halaguys.whistleon.dto.response;

import com.halaguys.whistleon.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class TeamSearchResponseDto {
    private int teamId;
    private String teamName;
    private String location;
    private String logo;
    private String description;
    private List<User> users;

    @Builder
    public TeamSearchResponseDto(int teamId, String teamName, String location, String logo, String description, List<User> users) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.location = location;
        this.logo = logo;
        this.description = description;
        this.users = users;
    }
}
