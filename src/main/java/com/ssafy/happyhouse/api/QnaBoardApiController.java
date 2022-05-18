package com.ssafy.happyhouse.api;

import com.ssafy.happyhouse.entity.Board;
import com.ssafy.happyhouse.entity.QnaBoard;
import com.ssafy.happyhouse.entity.Reply;
import com.ssafy.happyhouse.entity.User;
import com.ssafy.happyhouse.repository.dto.BoardDto;
import com.ssafy.happyhouse.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RequestMapping("/api/qna")
@RestController
public class QnaBoardApiController {

    private final BoardService boardService;

    @GetMapping
    public ResponseEntity<?> list() {
        return new ResponseEntity(boardService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detail(@PathVariable Integer id) {
        return new ResponseEntity<>(boardService.findOne(id), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Integer> save(@RequestBody BoardDto boardDto, HttpSession session) {
        Board board = new QnaBoard();
        board.setTitle(boardDto.getTitle());
        board.setContent(boardDto.getContent());
        board.setUser(new User(boardDto.getUserId(), boardDto.getPassword()));
        return new ResponseEntity<>(boardService.saveBoard(board), HttpStatus.OK);
    }

    @PostMapping("/{id}/reply")
    public ResponseEntity<Integer> replySave(@PathVariable Integer id, @RequestBody BoardDto boardDto) {
        Reply reply = new Reply();
        reply.setContent(boardDto.getContent());
        reply.setUser(new User(boardDto.getUserId(), boardDto.getPassword()));
        boardService.saveReply(id, reply);
        return new ResponseEntity<>(1, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> update(@PathVariable Integer id, @RequestBody QnaBoard board) {
        System.out.println("1232board = " + board);
        boardService.updateBoard(id, board);
        return new ResponseEntity<>(1, HttpStatus.OK);
    }

    @PutMapping("/{boardId}/reply/{replyId}")
    public ResponseEntity<Integer> replySave(@PathVariable Integer boardId, @PathVariable Integer replyId, @RequestBody BoardDto boardDto) {
        Reply reply = new Reply();
        reply.setContent(boardDto.getContent());
        boardService.updateReply(replyId, reply);
        return new ResponseEntity<>(1, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> delete(@PathVariable Integer id) {
        boardService.delete(id);
        return new ResponseEntity<>(1, HttpStatus.OK);
    }

    @DeleteMapping("/{boardId}/reply/{replyId}")
    public ResponseEntity<Integer> deleteReply(@PathVariable Integer boardId, @PathVariable Integer replyId) {
        boardService.deleteReply(boardId, replyId);
        return new ResponseEntity<>(1, HttpStatus.OK);
    }
}
