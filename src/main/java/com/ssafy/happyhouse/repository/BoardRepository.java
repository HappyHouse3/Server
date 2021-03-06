package com.ssafy.happyhouse.repository;

import com.ssafy.happyhouse.entity.board.Board;
import com.ssafy.happyhouse.entity.board.Notice;
import com.ssafy.happyhouse.entity.board.QnaBoard;
import com.ssafy.happyhouse.entity.board.RegionBoard;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final EntityManager em;

    public void save(Board board) {
        em.persist(board);
    }

    public Board findById(Integer id) {
        return em.find(Board.class, id);
    }

    public List<Notice> findAllNoticeBoard() {
        return em.createQuery("select b from Notice b join fetch b.user order by b.id desc", Notice.class)
                .getResultList();
    }

    public List<QnaBoard> findAllQnaBoard() {
        return em.createQuery("select q from QnaBoard q join fetch q.user order by q.id desc ", QnaBoard.class)
                .getResultList();
    }

    public List<RegionBoard> findAllRegionBoard(String sidoCode) {
        return em.createQuery("select b from RegionBoard b join fetch b.user where b.sido.sidoCode = :sidoCode order by b.id desc", RegionBoard.class)
                .setParameter("sidoCode", sidoCode)
                .getResultList();
    }

    public void delete(Board board) {
        em.remove(board);
    }
}
