package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.entity.Board;
import com.ssafy.happyhouse.entity.Reply;
import com.ssafy.happyhouse.repository.BoardRepository;
import com.ssafy.happyhouse.repository.ReplyRespository;
import com.ssafy.happyhouse.repository.dto.BoardDto;
import com.ssafy.happyhouse.repository.dto.ReplyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    public BoardDto findOne(Integer id) {
        Board b = boardRepository.findById(id);
        return new BoardDto(b.getId(), b.getTitle(), b.getContent(), b.getUser().getUserId(), b.getUser().getPassword(), getReplyList(b));
    }

    public List<BoardDto> findAll() {
        List<Board> result = boardRepository.findAll();
        return result.stream()
                .map(b -> new BoardDto(b.getId(), b.getTitle(), b.getContent(), b.getUser().getUserId(), b.getUser().getPassword(), getReplyList(b)))
                .collect(Collectors.toList());
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

    private List<ReplyDto> getReplyList(Board b) {
        return b.getReplyList().stream().map(r -> new ReplyDto(r.getId(), r.getContent(), r.getUser().getUserId(), r.getUser().getPassword(), r.getRegTime())).collect(Collectors.toList());
    }
}
