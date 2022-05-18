package com.ssafy.happyhouse.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class User {
    @Id @NotNull
    private String user_id;
    private String password;
}
