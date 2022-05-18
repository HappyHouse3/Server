package com.ssafy.happyhouse.entity.house;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "houseinfo")

@Entity @Getter @Setter
public class HouseInfo {
    @Id @Column(nullable = false)
    private Integer aptCode;

    @Column(nullable = false, length=50)
    private String aptName;

    @JoinColumn(name = "dong_code")
    @ManyToOne(fetch = FetchType.LAZY)
    private Dong dong;

    private String dongName;

    @Column(nullable = false)
    private Integer buildYear;

    @Column(nullable = false, length = 30)
    private String jibun;

    @Column(nullable = false, length = 30)
    private String lat;
    @Column(nullable = false, length = 30)
    private String lng;
    private String img;
}
