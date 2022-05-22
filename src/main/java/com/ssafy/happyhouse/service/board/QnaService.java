//package com.ssafy.happyhouse.service.board;
//
//import com.ssafy.happyhouse.dto.board.*;
//import com.ssafy.happyhouse.entity.User;
//import com.ssafy.happyhouse.entity.board.Board;
//import com.ssafy.happyhouse.entity.board.QnaBoard;
//import com.ssafy.happyhouse.entity.board.Reply;
//import com.ssafy.happyhouse.repository.BoardRepository;
//import com.ssafy.happyhouse.repository.ReplyRespository;
//import com.ssafy.happyhouse.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//public class QnaService {
//
//    private final BoardRepository boardRepository;
//    private final UserRepository userRepository;
//    private final ReplyRespository replyRespository;
//
//    @Transactional
//    public Integer saveBoard(BoardDto boardDto) {
//        QnaBoard board = new QnaBoard();
//        User user = userRepository.findById(boardDto.getId()).orElseThrow(() -> new RuntimeException("사용자가 없습니다."));
//        board.setTitle(boardDto.getTitle());
//        board.setContent(boardDto.getContent());
//        board.setUser(user);
//        boardRepository.save(board);
//        return board.getId();
//    }
//
//    public BoardDto findOne(Integer id) {
//        Board b = boardRepository.findById(id);
//        return new BoardDto(b.getId(), b.getTitle(), b.getContent(), b.getUser().getUserId(), b.getUser().getPassword(), b.getRegTime().toLocalDate(), getReplyList(b));
//    }
//
//    public List<BoardDto> findAll() {
//        List<QnaBoard> result = boardRepository.findAllQnaBoard();
//        return result.stream()
//                .map(b -> new BoardDto(b.getId(), b.getTitle(), b.getContent(), b.getUser().getUserId(), b.getUser().getPassword(), b.getRegTime().toLocalDate(), getReplyList(b)))
//                .collect(Collectors.toList());
//    }
//
//    @Transactional
//    public Integer updateBoard(Integer id, BoardUpdateDto updateBoardDto) {
//        try {
//            Board board = boardRepository.findById(id);
//            board.setTitle(updateBoardDto.getTitle());
//            board.setContent(updateBoardDto.getContent());
//            return board.getId();
//        } catch (Exception e) {
//            throw new IllegalArgumentException("해당 ID가 존재하지 않습니다.");
//        }
//    }
//
//    @Transactional
//    public void delete(Integer id) {
//        Board board = boardRepository.findById(id);
//        boardRepository.delete(board);
//    }
//
//    @Transactional
//    public void saveReply(Integer boardId, ReplyDto replyInputDto) {
//        User user = userRepository.findById(replyInputDto.getUserId()).orElseThrow(() -> new RuntimeException("사용자가 없습니다."));
//
//        Reply reply = new Reply();
//        reply.setContent(replyInputDto.getContent());
//        reply.setUser(user);
//
//        Board board = boardRepository.findById(boardId);
//        board.addReply(reply);
//    }
//
//    @Transactional
//    public void updateReply(Integer replyId, ReplyUpdateDto updateDto) {
//        Reply reply = replyRespository.findById(replyId)
//                .orElseThrow(() -> new IllegalArgumentException("ID가 존재하지 않습니다."));
//        reply.setContent(updateDto.getContent());
//    }
//
//    @Transactional
//    public void deleteReply(Integer replyId) {
//        Reply reply = replyRespository.findById(replyId)
//                .orElseThrow(() -> new IllegalArgumentException("해당 ID가 존재하지 않습니다."));
//        replyRespository.delete(reply);
//    }
//
//    private List<ReplyDto> getReplyList(Board b) {
//        return b.getReplyList().stream().map(r -> new ReplyDto(r.getId(), r.getContent(), String.valueOf(r.getUser().getUserId()) , r.getUser().getPassword(), r.getRegTime())).collect(Collectors.toList());
//    }
//}
