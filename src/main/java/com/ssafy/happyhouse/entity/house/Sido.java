package com.ssafy.happyhouse.entity.house;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Sido {
    @Id @Column(name = "sidoCode", nullable = false, length = 10)
    private String sidoCode;
    @Column(name = "sidoName", nullable = false, unique = true)
    private String sidoName;
}
