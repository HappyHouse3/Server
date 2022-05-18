package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.entity.Board;
import com.ssafy.happyhouse.entity.Reply;
import com.ssafy.happyhouse.repository.BoardRepository;
import com.ssafy.happyhouse.repository.ReplyRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    private final BoardRepository boardRepository;
    private final ReplyRespository replyRespository;

    @Transactional
    public Integer saveBoard(Board board) {
        boardRepository.save(board);
        return board.getId();
    }

    public Board findOne(Integer id) {
        return boardRepository.findById(id);
    }

    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    @Transactional
    public Integer updateBoard(Integer id, Board updateBoardDto) throws IllegalArgumentException {
        try {
            Board board = boardRepository.findById(id);
            board.setTitle(updateBoardDto.getTitle());
            board.setContent(updateBoardDto.getContent());
            return board.getId();
        } catch (Exception e) {
            throw new IllegalArgumentException("해당 ID가 존재하지 않습니다.");
        }
    }

    @Transactional
    public void delete(Integer id) {
        Board board = boardRepository.findById(id);
        boardRepository.delete(board);
    }

    @Transactional
    public void saveReply(Integer boardId, Reply reply) {
        Board board = boardRepository.findById(boardId);
        board.addReply(reply);
    }

    @Transactional
    public void updateReply(Integer replyId, Reply updateDto) {
        Reply reply = replyRespository.findById(replyId)
                .orElseThrow(() -> new IllegalArgumentException("ID가 존재하지 않습니다."));
        reply.setContent(updateDto.getContent());
    }

    @Transactional
    public void deleteReply(Integer boardId, Integer replyId) {
        Reply reply = replyRespository.findById(replyId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID가 존재하지 않습니다."));
        replyRespository.delete(reply);
    }
}
