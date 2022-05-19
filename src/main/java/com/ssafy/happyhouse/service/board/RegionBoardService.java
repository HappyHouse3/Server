package com.ssafy.happyhouse.service.board;

import com.ssafy.happyhouse.dto.board.BoardDto;
import com.ssafy.happyhouse.dto.board.BoardInputDto;
import com.ssafy.happyhouse.dto.board.BoardUpdateDto;
import com.ssafy.happyhouse.dto.board.ReplyDto;
import com.ssafy.happyhouse.entity.User;
import com.ssafy.happyhouse.entity.board.Board;
import com.ssafy.happyhouse.entity.board.RegionBoard;
import com.ssafy.happyhouse.entity.board.Reply;
import com.ssafy.happyhouse.entity.house.Sido;
import com.ssafy.happyhouse.repository.BoardRepository;
import com.ssafy.happyhouse.repository.HouseRepository;
import com.ssafy.happyhouse.repository.ReplyRespository;
import com.ssafy.happyhouse.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegionBoardService {

    private final BoardRepository boardRepository;
    private final HouseRepository houseRepository;
    private final ReplyRespository replyRespository;
    private final UserRepository userRepository;

    @Transactional
    public Integer saveBoard(BoardInputDto boardDto, String sidoCode) {
        RegionBoard board = new RegionBoard();
        Sido sido = houseRepository.findSidoById(sidoCode);
        User user = userRepository.findById(boardDto.getUserNo());
        board.setTitle(boardDto.getTitle());
        board.setContent(boardDto.getContent());
        board.setUser(user);
        board.setSido(sido);
        boardRepository.save(board);
        return board.getId();
    }

    public BoardDto findOne(Integer id) {
        Board b = boardRepository.findById(id);
        return new BoardDto(b.getId(), b.getTitle(), b.getContent(), b.getUser().getUserId(), b.getUser().getPassword(), b.getRegTime().toLocalDate(), getReplyList(b));
    }

    public List<BoardDto> findAll(String sido) {
        List<RegionBoard> result = boardRepository.findAllRegionBoard(sido);
        return result.stream()
                .map(b -> new BoardDto(b.getId(), b.getTitle(), b.getContent(), b.getUser().getUserId(), b.getUser().getPassword(), b.getRegTime().toLocalDate(), getReplyList(b)))
                .collect(Collectors.toList());
    }

    @Transactional
    public Integer updateBoard(Integer id, BoardUpdateDto boardUpdateDto) throws IllegalArgumentException {
        try {
            Board board = boardRepository.findById(id);
            board.setTitle(boardUpdateDto.getTitle());
            board.setContent(boardUpdateDto.getContent());
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
        return b.getReplyList().stream().map(r -> new ReplyDto(r.getId(), r.getContent(), String.valueOf(r.getUser().getId()), r.getUser().getUserId(), r.getUser().getPassword(), r.getRegTime())).collect(Collectors.toList());
    }
}
