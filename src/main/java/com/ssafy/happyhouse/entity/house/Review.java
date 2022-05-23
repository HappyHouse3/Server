package com.ssafy.happyhouse.entity.house;

import com.ssafy.happyhouse.entity.board.Board;

import javax.persistence.*;

//@Entity
public class Review {
    @Id @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

}
