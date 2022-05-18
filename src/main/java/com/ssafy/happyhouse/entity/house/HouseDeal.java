package com.ssafy.happyhouse.entity.house;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity @Getter @Setter
@Table(name = "housedeal")
public class HouseDeal {
    @Id @Column(nullable = false) @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name="apt_code")
    @ManyToOne(fetch = FetchType.LAZY)
    private HouseInfo houseInfo;

    @Column(name="dealAmount", length=20)
    private String dealAmount;

    @Column(name="dealYear")
    private Integer dealYear;

    @Column(name="dealMonth")
    private Integer dealMonth;

    @Column(name="dealDay")
    private Integer dealDay;

    @Column(length=30)
    private String area;

    @Column(length=30)
    private String floor;

    @Column(length=30)
    private String type;

    @Column(name="rentMoney", length=30)
    private String rentMoney;
}