package com.ssafy.happyhouse.service.board;

import com.ssafy.happyhouse.dto.board.BoardDto;
import com.ssafy.happyhouse.dto.board.BoardInputDto;
import com.ssafy.happyhouse.dto.board.BoardUpdateDto;
import com.ssafy.happyhouse.dto.board.NoticeDto;
import com.ssafy.happyhouse.entity.User;
import com.ssafy.happyhouse.entity.board.Board;
import com.ssafy.happyhouse.entity.board.Notice;
import com.ssafy.happyhouse.repository.BoardRepository;
import com.ssafy.happyhouse.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoticeService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional
    public Integer saveBoard(BoardInputDto boardDto) {
        User user = getUser(boardDto);
        Board board = new Notice();
        board.setTitle(boardDto.getTitle());
        board.setContent(boardDto.getContent());
        board.setUser(user);
        boardRepository.save(board);

        return board.getId();
    }

    public NoticeDto findOne(Integer id) {
        try {
            Board b = boardRepository.findById(id);
            return NoticeDto.builder()
                    .id(b.getId())
                    .title(b.getTitle())
                    .content(b.getContent())
                    .userNo(b.getUser().getId())
                    .userNickName(b.getUser().getNickName())
                    .regTime(b.getRegTime().format(DateTimeFormatter.ofPattern("M월 dd일 HH:mm")))
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<NoticeDto> findAll() {
        List<Notice> result = boardRepository.findAllNoticeBoard();
        return result.stream()
                .map(b -> {
                    NoticeDto noticeDto = new NoticeDto();
                    noticeDto.setId(b.getId());
                    noticeDto.setTitle(b.getTitle());
                    noticeDto.setContent(b.getContent());
                    noticeDto.setUserNickName(b.getUser().getNickName());
                    noticeDto.setRegTime(b.getRegTime().format(DateTimeFormatter.ofPattern("M월 dd일 HH:mm")));
                    return noticeDto;
                }).collect(Collectors.toList());
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

    private User getUser(BoardInputDto boardDto) {
        User user = userRepository.findById(boardDto.getUserNo()).orElseThrow(() -> new UsernameNotFoundException("해당 사용자가 존재하지 않습니다."));
        return user;
    }
}
