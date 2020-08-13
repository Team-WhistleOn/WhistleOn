package com.halaguys.whistleon.domain.team;

import com.halaguys.whistleon.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
public class Team {
    @Id @GeneratedValue
    @Column(name = "team_id")
    private int teamId;

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

}
