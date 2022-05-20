package com.ssafy.happyhouse.repository;

import com.ssafy.happyhouse.dto.house.SidoDto;
import com.ssafy.happyhouse.entity.house.Dong;
import com.ssafy.happyhouse.entity.house.HouseDeal;
import com.ssafy.happyhouse.entity.house.HouseInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class HouseRepository {

    private final EntityManager em;

    public List<String> getSido() {
        List<String> resultList = em.createQuery("select distinct d.sidoName from Dong d ", String.class)
                .getResultList();
        resultList.stream()
                .map(s -> new SidoDto(em.createQuery("select d.dongCode from Dong d where d.sidoName = :sidoName", String.class)
                        .setParameter("sidoName", s)
                        .getFirstResult(), s),)
    }

    public List<String> getGugunInSido(String sidoName) {
        return em.createQuery("select distinct d.gugunName from Dong d where d.sidoName = :sidoName and d.gugunName is not null", String.class)
                        .setParameter("sidoName", sidoName)
                        .getResultList();
    }

    public List<Dong> getDongInGugun(String gugunName){
         return em.createQuery("select d from Dong d where d.gugunName = :gugunName and d.dongName is not null", Dong.class)
                .setParameter("gugunName", gugunName)
                .getResultList();
    }

    public List<HouseInfo> getAptInDong(String dong) {
        return em.createQuery("select hi from HouseInfo hi join fetch hi.dong d where d.dongCode = :dong")
                .setParameter("dong", dong)
                .getResultList();
    }

    public List<HouseDeal> getHouseDeal(String aptcode) {
        return em.createQuery("select hd from HouseDeal hd join fetch hd.houseInfo hi where substring(hi.aptCode, 1, 4) = :aptcode")
                .setParameter("aptcode", aptcode)
                .getResultList();
    }
}
