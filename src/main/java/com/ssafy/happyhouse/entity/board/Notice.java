package com.ssafy.happyhouse.entity.board;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("N")
public class Notice extends Board {
}
