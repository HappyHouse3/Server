package com.ssafy.happyhouse.entity.board;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@DiscriminatorValue("R")
public class RegionBoard extends Board {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sido_code")
    private Sido sido;

    @Lob
    private String img;
}
