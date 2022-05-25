package com.ssafy.happyhouse.repository;

import com.ssafy.happyhouse.entity.WatchList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class WatchListRepository {

    private final EntityManager em;

    public List<WatchList> findWatchListbyUserId(Long userId) {
        return em.createQuery("select w from WatchList w join fetch w.houseInfo join fetch w.user where w.user.id = :userId", WatchList.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    public void save(WatchList watchList) {
        em.persist(watchList);
    }

    public void removeWatchList(Long watchListId) {
        WatchList watchList = em.find(WatchList.class, watchListId);
        em.remove(watchList);
    }
}
