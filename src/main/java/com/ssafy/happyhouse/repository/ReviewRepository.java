package com.ssafy.happyhouse.repository;

import com.ssafy.happyhouse.entity.house.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
}
