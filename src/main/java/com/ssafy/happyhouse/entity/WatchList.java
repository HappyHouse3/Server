package com.ssafy.happyhouse.entity;

import com.ssafy.happyhouse.entity.house.HouseInfo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class WatchList {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "house_info")
    private HouseInfo houseInfo;
}
