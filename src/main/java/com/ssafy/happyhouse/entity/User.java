package com.ssafy.happyhouse.entity;

import com.ssafy.happyhouse.entity.board.Sido;
import com.ssafy.happyhouse.entity.house.Dong;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Data
@NoArgsConstructor
@Entity
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, unique = true)
    private String userId;

    @Column(length = 100)
    private String password;

    @Column(length = 50)
    private String nickName;

    private String email;

    private String roles;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sido_code")
    private Sido sido;

    public User(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    @Builder
    public User(String userName, String password, String nickName, String email) {
        this.userId = userName;
        this.password = password;
        this.nickName = nickName;
        this.email = email;
        setRoleUser();
    }

    public void setUserName(String userName) {
        this.userId = userName;
    }

    public String getUserName() {
        return userId;
    }

    public List<String> getRoleList() {
        if(this.roles.length() > 0) {
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

    public void setRoleUser() {
        this.roles = "ROLE_USER";
    }

    public void setRoleAdmin() {
        this.roles = "ROLE_USER,ROLE_ADMIN";
    }
}
