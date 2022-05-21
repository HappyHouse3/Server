package com.ssafy.happyhouse.entity.house;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity @Getter @Setter
@Table(name = "housedeal")
public class HouseDeal {
    @Id @Column(nullable = false) @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name="apt_code")
    @ManyToOne(fetch = FetchType.LAZY)
    private HouseInfo houseInfo;

    @Column(length=20)
    private String dealAmount;

    private Integer dealYear;

    private Integer dealMonth;

    private Integer dealDay;

    @Column(length=30)
    private String area;

    @Column(length=4)
    private String floor;

    @Column(length = 1, name = "type")
    private String cancelDealType;
}