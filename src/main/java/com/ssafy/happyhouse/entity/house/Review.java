package com.ssafy.happyhouse.entity.house;

import com.ssafy.happyhouse.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Review {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @Lob
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apt_code")
    private HouseInfo houseInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private float score;
}
