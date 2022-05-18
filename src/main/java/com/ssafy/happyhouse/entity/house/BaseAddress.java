package com.ssafy.happyhouse.entity.house;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@Table(name = "baseaddress")
@Entity
public class BaseAddress {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 30)
    private String sidoName;

    @Column(length = 30)
    private String gugunName;

    @JoinColumn(name = "dong_code")
    @ManyToOne
    private Dong dong;

    private String dongName;

    @Column(length = 20)
    private String lat;

    @Column(length = 20)
    private String lng;
}
