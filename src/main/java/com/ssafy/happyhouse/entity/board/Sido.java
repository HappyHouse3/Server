package com.ssafy.happyhouse.entity.board;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter @Setter
public class Sido {
    @Id
    @Column(nullable = false, length=10)
    private String sidoCode;

    @Column(length=30)
    private String sidoName;
}
