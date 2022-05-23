package com.ssafy.happyhouse.entity.house;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter @Setter
@Table(name = "houseinfo")
public class HouseInfo {
    @Id @Column(nullable = false)
    private Long aptCode;

    @Column(nullable = false, length=40)
    private String aptName;

    @JoinColumn(name = "dong_code")
    @ManyToOne(fetch = FetchType.LAZY)
    private Dong dong;

    @Column(length = 40)
    private String dongName;

    @Column()
    private Integer buildYear;

    @Column(length = 40)
    private String roadName;

    @Column(length = 5)
    private String roadNameBonbun;

    @Column(length = 5)
    private String roadNameBubun;

    @Column(length = 2)
    private String roadNameSeq;

    @Column(length = 1)
    private String roadNameBasementCode;

    @Column(length = 7)
    private String roadNameCode;

    @Column(length = 4)
    private String bonbun;

    @Column(length = 4)
    private String bubun;

    @Column(length = 5)
    private String sigunguCode;

    @Column(length = 5)
    private String eubmyundongCode;

    @Column(length = 1)
    private String landCode;

    @Column(length = 10)
    private String jibun;

    @Column(length = 30)
    private String lat;

    @Column(length = 30)
    private String lng;

    @OneToMany(mappedBy = "houseInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Review> reviews = new ArrayList<>();

    public void addReview(Review review) {
        reviews.add(review);
        review.setHouseInfo(this);
    }
}
