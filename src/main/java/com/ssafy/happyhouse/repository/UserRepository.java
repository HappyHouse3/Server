package com.ssafy.happyhouse.repository;

import com.ssafy.happyhouse.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u join fetch u.sido")
    Optional<User> findByUserId(String userId);
}
