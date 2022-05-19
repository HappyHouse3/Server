package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.dto.board.BoardInputDto;
import com.ssafy.happyhouse.dto.board.BoardUpdateDto;
import com.ssafy.happyhouse.dto.house.NoticeDto;
import com.ssafy.happyhouse.entity.User;
import com.ssafy.happyhouse.entity.board.Board;
import com.ssafy.happyhouse.entity.board.Notice;
import com.ssafy.happyhouse.repository.BoardRepository;
import com.ssafy.happyhouse.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional
    public Integer saveBoard(BoardInputDto boardDto) {
        Board board = new Notice();
        User user = userRepository.findById(boardDto.getUserNo());
        board.setTitle(boardDto.getTitle());
        board.setContent(boardDto.getContent());
        board.setUser(user);
        boardRepository.save(board);

        return board.getId();
    }

    public NoticeDto findOne(Integer id) {
        try {
            Board b = boardRepository.findById(id);
            return new NoticeDto(b.getId(), b.getTitle(), b.getContent(), b.getUser().getUserId(), b.getUser().getPassword(), b.getRegTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<NoticeDto> findAll() {
        List<Notice> result = boardRepository.findAllNoticeBoard();
        return result.stream()
                .map(b -> new NoticeDto(b.getId(), b.getTitle(), b.getContent(), b.getUser().getUserId(), b.getUser().getPassword(), b.getRegTime()))
                .collect(Collectors.toList());
    }

    @Transactional
    public Integer updateBoard(Integer id, BoardUpdateDto updateBoardDto) throws IllegalArgumentException {
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
}
