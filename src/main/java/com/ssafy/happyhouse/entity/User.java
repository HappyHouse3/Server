package com.ssafy.happyhouse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String userId;
    private String password;
}
