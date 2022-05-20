package com.ssafy.happyhouse.api.board;

import com.ssafy.happyhouse.dto.board.*;
import com.ssafy.happyhouse.entity.User;
import com.ssafy.happyhouse.entity.board.Reply;
import com.ssafy.happyhouse.service.board.RegionBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/api/regionboard/{sido}")
@RestController
public class RegionBoardApiController {

    private final RegionBoardService boardService;

    @GetMapping
    public ResponseEntity<?> list(@PathVariable String sido) {
        return new ResponseEntity(boardService.findAll(sido), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detail(@PathVariable Integer id) {
        return new ResponseEntity<>(boardService.findOne(id), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Integer> save(@PathVariable String sido, @RequestBody BoardInputDto boardDto) {
        return new ResponseEntity<>(boardService.saveBoard(boardDto, sido), HttpStatus.OK);
    }

    @PostMapping("/{id}/reply")
    public ResponseEntity<Integer> replySave(@PathVariable Integer id, @RequestBody ReplyInputDto replyDto) {
        boardService.saveReply(id, replyDto);
        return new ResponseEntity<>(1, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> update(@PathVariable Integer id, @RequestBody BoardUpdateDto board) {
        boardService.updateBoard(id, board);
        return new ResponseEntity<>(1, HttpStatus.OK);
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
    public ResponseEntity<Integer> deleteReply(@PathVariable Integer boardId, @PathVariable Integer replyId) {
        boardService.deleteReply(replyId);
        return new ResponseEntity<>(1, HttpStatus.OK);
    }
}
