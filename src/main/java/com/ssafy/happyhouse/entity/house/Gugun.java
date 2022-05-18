package com.ssafy.happyhouse.entity.house;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity @Getter @Setter
public class Gugun {
    @Id @Column(name="gugunCode", nullable = false, length=10)
    private String gugunCode;

    @Column(name="gugunName", length=30)
    private String gugunName;
}
