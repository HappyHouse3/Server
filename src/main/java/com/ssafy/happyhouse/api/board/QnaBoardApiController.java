package com.ssafy.happyhouse.api.board;

import com.ssafy.happyhouse.dto.board.*;
import com.ssafy.happyhouse.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@RequiredArgsConstructor
@RequestMapping("/api/qna")
@RestController
public abstract class QnaBoardApiController extends BoardService {

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
    public ResponseEntity<Integer> save(@RequestBody BoardInputDto boardDto) {
        return new ResponseEntity<>(boardService.saveBoard(boardDto), HttpStatus.OK);
    }

    @PostMapping("/{boardId}/reply")
    public ResponseEntity<Integer> replySave(@PathVariable Integer boardId, @RequestBody ReplyDto replyInputDto) {
        boardService.saveReply(boardId, replyInputDto);
        return new ResponseEntity<>(1, HttpStatus.OK);
    }

    @PutMapping("/{boardId}")
    public ResponseEntity<Integer> update(@PathVariable Integer boardId, @RequestBody BoardUpdateDto updateDto) {
        boardService.updateBoard(boardId, updateDto);
        return new ResponseEntity<>(boardId, HttpStatus.OK);
    }

    @PutMapping("/{boardId}/reply/{replyId}")
    public ResponseEntity<Integer> replyUpdate(@PathVariable Integer boardId, @PathVariable Integer replyId, @RequestBody ReplyUpdateDto replyUpdateDto) {
        boardService.updateReply(replyId, replyUpdateDto);
        return new ResponseEntity<>(1, HttpStatus.OK);
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<Integer> delete(@PathVariable Integer boardId) {
        boardService.delete(boardId);
        return new ResponseEntity<>(1, HttpStatus.OK);
    }

    @DeleteMapping("/{boardId}/reply/{replyId}")
    public ResponseEntity<Integer> deleteReply(@PathVariable Integer replyId) {
        boardService.deleteReply(replyId);
        return new ResponseEntity<>(1, HttpStatus.OK);
    }
}
