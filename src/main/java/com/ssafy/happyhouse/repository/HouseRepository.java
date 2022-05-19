package com.ssafy.happyhouse.repository;

import com.ssafy.happyhouse.entity.house.HouseDeal;
import com.ssafy.happyhouse.entity.house.HouseInfo;
import com.ssafy.happyhouse.dto.house.DongDto;
import com.ssafy.happyhouse.dto.house.SidoGugunCodeDto;
import com.ssafy.happyhouse.entity.house.Sido;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class HouseRepository {

    private final EntityManager em;

    public List<SidoGugunCodeDto> getSido() {
        return em.createQuery("select new com.ssafy.happyhouse.repository.dto.SidoGugunCodeDto(substring(s.sidoCode, 1, 2), s.sidoName, '쓰면안됨', '쓰면안됨') from Sido s order by s.sidoCode", SidoGugunCodeDto.class)
                .getResultList();
    }

    public List<SidoGugunCodeDto> getGugunInSido(String sido) {
        return em.createQuery("" +
                        "select new com.ssafy.happyhouse.repository.dto.SidoGugunCodeDto('쓰면안됨', '쓰면안됨', substring(g.gugunCode, 1, 5), g.gugunName) " +
                        "from Gugun g " +
                        "where substring(g.gugunCode, 1, 2) = :sido order by g.gugunCode", SidoGugunCodeDto.class)
                        .setParameter("sido", sido)
                        .getResultList();
    }

    public List<DongDto> getDongInGugun(String gugun){
        String jpql =
                "select distinct new com.ssafy.happyhouse.repository.dto.DongDto(d.dongName, d.dongCode) " +
                "from HouseInfo h join h.dong d on substring(d.dongCode, 1, 5) = :gugun " +
                "order by d.dongName";

         return em.createQuery(jpql, DongDto.class)
                .setParameter("gugun", gugun)
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

    public Sido findSidoById(String sidoCode) {
        return em.find(Sido.class, sidoCode);
    }
}
