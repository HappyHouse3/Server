package com.ssafy.happyhouse.api.board;

import com.ssafy.happyhouse.dto.board.*;
import com.ssafy.happyhouse.service.board.QnaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/api/qna")
@RestController
public class QnaBoardApiController {

    private final QnaService boardService;

    @GetMapping
    public ResponseEntity<?> list() {
        return new ResponseEntity(boardService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detail(@PathVariable Integer id) {
        return new ResponseEntity<>(boardService.findOne(id), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Integer> save(@RequestBody BoardInputDto boardDto, HttpSession session) {
        return new ResponseEntity<>(boardService.saveBoard(boardDto), HttpStatus.OK);
    }

    @PostMapping("/{id}/reply")
    public ResponseEntity<Integer> replySave(@PathVariable Integer id, @RequestBody ReplyInputDto replyInputDto) {
        boardService.saveReply(id, replyInputDto);
        return new ResponseEntity<>(1, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> update(@PathVariable Integer id, @RequestBody BoardUpdateDto updateDto) {
        boardService.updateBoard(id, updateDto);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PutMapping("/{boardId}/reply/{replyId}")
    public ResponseEntity<Integer> replyUpdate(@PathVariable Integer boardId, @PathVariable Integer replyId, @RequestBody ReplyUpdateDto replyUpdateDto) {
        boardService.updateReply(replyId, replyUpdateDto);
        return new ResponseEntity<>(1, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> delete(@PathVariable Integer id) {
        boardService.delete(id);
        return new ResponseEntity<>(1, HttpStatus.OK);
    }

    @DeleteMapping("/{boardId}/reply/{replyId}")
    public ResponseEntity<Integer> deleteReply(@PathVariable Integer replyId) {
        boardService.deleteReply(replyId);
        return new ResponseEntity<>(1, HttpStatus.OK);
    }
}
