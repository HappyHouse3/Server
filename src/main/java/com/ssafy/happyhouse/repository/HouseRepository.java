package com.ssafy.happyhouse.repository;

import com.ssafy.happyhouse.dto.house.GugunDto;
import com.ssafy.happyhouse.dto.house.SidoDto;
import com.ssafy.happyhouse.entity.board.Sido;
import com.ssafy.happyhouse.entity.house.Dong;
import com.ssafy.happyhouse.entity.house.HouseDeal;
import com.ssafy.happyhouse.entity.house.HouseInfo;
import com.ssafy.happyhouse.entity.house.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class HouseRepository {

    private final EntityManager em;

    public List<SidoDto> getSido() {
        return em.createQuery("select new com.ssafy.happyhouse.dto.house.SidoDto(substring(s.sidoCode, 1, 2), s.sidoName) from Sido s ", SidoDto.class)
                .getResultList();
    }

    public List<SidoDto> findSidoList() {
        return em.createQuery("select new com.ssafy.happyhouse.dto.house.SidoDto(s.sidoCode, s.sidoName) from Sido s ", SidoDto.class)
                .getResultList();
    }

    public Sido findSidoById(String sidoCode) {
        return em.find(Sido.class, sidoCode);
    }

    public List<GugunDto> getGugunInSido(String sidoCode) {
        List<String> gugunCode = em.createQuery("select distinct substring(d.dongCode, 1, 5) from Dong d where substring(d.dongCode, 1, 2) = :sidoCode and d.dongName is not null", String.class)
                .setParameter("sidoCode", sidoCode)
                .getResultList();

        List<GugunDto> result = new ArrayList<>();

        for (String gcode : gugunCode) {
            String gugunName = em.createQuery("select distinct d.gugunName from Dong d where substring(d.dongCode, 1, 5) = :gcode and d.dongName is not null", String.class)
                    .setParameter("gcode", gcode)
                    .getSingleResult();
            result.add(new GugunDto(gcode, gugunName));
        }

        System.out.println("result = " + result);

        return result;
    }

    public List<Dong> getDongInGugun(String gugunCode){
         return em.createQuery("select d from Dong d where substring(d.dongCode, 1, 5) = :gugunCode and d.dongName is not null", Dong.class)
                .setParameter("gugunCode", gugunCode)
                .getResultList();
    }

    public Dong findDongbyDongCode(String dongCode) {
        return em.createQuery("select d from Dong d where d.dongCode = :dongCode and d.dongName is not null", Dong.class)
                .setParameter("dongCode", dongCode)
                .getSingleResult();
    }

    public List<HouseInfo> getAptInDong(String dongCode) {
        return em.createQuery("select hi from HouseInfo hi join fetch hi.dong d where d.dongCode = :dongCode")
                .setParameter("dongCode", dongCode)
                .getResultList();
    }

    public List<HouseDeal> getHouseDeal(Long aptcode) {
        return em.createQuery("select hd from HouseDeal hd join fetch hd.houseInfo hi where hi.aptCode = :aptcode order by hd.dealYear desc, hd.dealMonth desc, hd.dealDay DESC")
                .setParameter("aptcode", aptcode)
                .getResultList();
    }

    public List<Review> getHouseReview(Long aptCode) {
        return em.createQuery("select rv from Review rv join fetch rv.user where rv.houseInfo.aptCode = :aptCode order by rv.id desc")
                .setParameter("aptCode", aptCode)
                .getResultList();
    }

    public Review saveReview(Review review) {
        em.persist(review);
        System.out.println("review = " + review);
        return review;
    }

    public Review findReviewById(Integer id) {
        return em.find(Review.class, id);
    }

    public HouseInfo getApt(Long aptCode) {
        return em.find(HouseInfo.class, aptCode);
    }

    public void deleteReview(Review review) {
        em.remove(review);
    }
}
