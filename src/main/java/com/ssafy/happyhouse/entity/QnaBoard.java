package com.ssafy.happyhouse.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "Q")
public class QnaBoard extends Board {
}
