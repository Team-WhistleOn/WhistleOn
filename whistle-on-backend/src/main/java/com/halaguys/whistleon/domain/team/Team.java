package com.halaguys.whistleon.domain.team;

import com.halaguys.whistleon.domain.user.User;
import com.halaguys.whistleon.dto.request.TeamModifyRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@DynamicInsert
@Getter
@NoArgsConstructor
@Entity
public class Team {
    @Id @GeneratedValue
    @Column(name = "team_id")
    private Long teamId;

    @Column(name = "team_name")
    private String teamName;

    @Column(name = "location")
    private String location;

    //이미지 not yet..
    @Column(name="logo")
    private String logo;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "team")
    private List<User> users = new ArrayList<>();

    @Builder
    public Team(String teamName, String location, String logo, String description) {
        this.teamName=teamName;
        this.location=location;
        this.logo=logo;
        this.description=description;
    }

    public void update(Long id, TeamModifyRequestDto dto){
        this.teamName=dto.getTeamName();
        this.location=dto.getLocation();
        this.logo=dto.getLogo();
        this.description=dto.getDescription();
    }

}
