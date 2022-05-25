package com.ssafy.happyhouse.service.board;

import com.ssafy.happyhouse.dto.board.*;
import com.ssafy.happyhouse.entity.User;
import com.ssafy.happyhouse.entity.board.Board;
import com.ssafy.happyhouse.entity.board.RegionBoard;
import com.ssafy.happyhouse.entity.board.Reply;
import com.ssafy.happyhouse.entity.board.Sido;
import com.ssafy.happyhouse.repository.BoardRepository;
import com.ssafy.happyhouse.repository.HouseRepository;
import com.ssafy.happyhouse.repository.ReplyRespository;
import com.ssafy.happyhouse.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RegionBoardService {

    private final BoardRepository boardRepository;
    private final HouseRepository houseRepository;
    private final ReplyRespository replyRespository;
    private final UserRepository userRepository;

    public List<BoardDto> findAll(String sidoCode) {
        List<RegionBoard> result = boardRepository.findAllRegionBoard(sidoCode);
        return result.stream()
                .map(b -> {
                    List<ReplyDto> replies = b.getReplyList().stream().map(r -> {
                        ReplyDto replyDto = new ReplyDto();
                        replyDto.setId(r.getId());
                        replyDto.setContent(r.getContent());
                        replyDto.setUserNickName(r.getUser().getNickName());
                        replyDto.setRegTime(r.getRegTime());
                        return replyDto;
                    }).collect(Collectors.toList());

                    return BoardDto.builder()
                            .id(b.getId())
                            .title(b.getTitle())
                            .content(b.getContent())
                            .userNickName(b.getUser().getNickName())
                            .regTime(b.getRegTime())
                            .replyList(replies)
                            .build();
                }).collect(Collectors.toList());
    }

    public BoardDto findOne(Integer id) {
        try {
            Board b = boardRepository.findById(id);
            List<Reply> result = b.getReplyList();
            List<ReplyDto> replyDtos = result.stream().map(r -> {
                ReplyDto replyDto = new ReplyDto();
                replyDto.setId(r.getId());
                replyDto.setContent(r.getContent());
                replyDto.setUserNickName(r.getUser().getNickName());
                replyDto.setRegTime(r.getRegTime());
                return replyDto;
            }).collect(Collectors.toList());

            return BoardDto.builder()
                    .id(b.getId())
                    .title(b.getTitle())
                    .content(b.getContent())
                    .userNo(b.getUser().getId())
                    .userNickName(b.getUser().getNickName())
                    .regTime(b.getRegTime())
                    .replyList(replyDtos)
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Transactional
    public Integer saveBoard(String sidoCode, RegionBoardDto boardDto) {
        User user = getUser(boardDto.getUserNo());
        Sido sido = houseRepository.findSidoById(sidoCode);

        RegionBoard board = new RegionBoard();
        board.setTitle(boardDto.getTitle());
        board.setContent(boardDto.getContent());
        board.setUser(user);
        board.setSido(sido);

        boardRepository.save(board);
        return board.getId();
    }

    @Transactional
    public void saveReply(Integer boardId, ReplyDto replyDto) {
        User user = getUser(replyDto.getUserNo());

        Reply reply = new Reply();
        reply.setContent(replyDto.getContent());
        reply.setUser(user);

        Board board = boardRepository.findById(boardId);
        board.addReply(reply);
    }

    @Transactional
    public Integer updateBoard(Integer id, BoardUpdateDto boardUpdateDto) {
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
    public void updateReply(Integer replyId, ReplyUpdateDto updateDto) {
        Reply reply = replyRespository.findById(replyId)
                .orElseThrow(() -> new IllegalArgumentException("ID가 존재하지 않습니다."));
        reply.setContent(updateDto.getContent());
    }

    @Transactional
    public void delete(Integer id) {
        Board board = boardRepository.findById(id);
        boardRepository.delete(board);
    }

    @Transactional
    public void deleteReply(Integer replyId) {
        Reply reply = replyRespository.findById(replyId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID가 존재하지 않습니다."));
        replyRespository.delete(reply);
    }

    private User getUser(Long userNo) {
        User user = userRepository.findById(userNo).orElseThrow(() -> new UsernameNotFoundException("해당 사용자가 존재하지 않습니다."));
        return user;
    }
}
