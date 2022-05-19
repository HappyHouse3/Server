package com.ssafy.happyhouse.entity.house;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Dong {
    @Id
    @Column(nullable = false, length=10)
    private String dongCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sido_code")
    private Sido sido;

    private String sidoName;

    @Column(length=30)
    private String gugunName;

    @Column(length=30)
    private String dongName;
}
