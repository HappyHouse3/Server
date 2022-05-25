package com.ssafy.happyhouse.api.board;

import com.ssafy.happyhouse.dto.board.*;
import com.ssafy.happyhouse.entity.User;
import com.ssafy.happyhouse.entity.board.Reply;
import com.ssafy.happyhouse.service.board.BoardService;
import com.ssafy.happyhouse.service.board.RegionBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/{sidoCode}/regionboard")
@RestController
public class RegionBoardApiController {

    private final RegionBoardService boardService;

    @GetMapping
    public ResponseEntity<List<RegionBoardDto>> list(@PathVariable String sidoCode) {
        return new ResponseEntity(boardService.findAll(sidoCode), HttpStatus.OK);
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<RegionBoardDto> detail(@PathVariable Integer boardId) {
        return new ResponseEntity<>(boardService.findOne(boardId), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Integer> save(@PathVariable String sidoCode, @RequestBody RegionBoardDto boardDto) {
        return new ResponseEntity<>(boardService.saveBoard(sidoCode, boardDto), HttpStatus.OK);
    }

    @PostMapping("/{boardId}/reply")
    public ResponseEntity<Integer> replySave(@PathVariable Integer boardId, @RequestBody ReplyDto replyDto) {
        boardService.saveReply(boardId, replyDto);
        return new ResponseEntity<>(1, HttpStatus.OK);
    }

    @PutMapping("/{boardId}")
    public ResponseEntity<Integer> update(@PathVariable Integer boardId, @RequestBody BoardUpdateDto boardUpdateDto) {
        boardService.updateBoard(boardId, boardUpdateDto);
        return new ResponseEntity<>(1, HttpStatus.OK);
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
    public ResponseEntity<Integer> deleteReply(@PathVariable Integer boardId, @PathVariable Integer replyId) {
        boardService.deleteReply(replyId);
        return new ResponseEntity<>(1, HttpStatus.OK);
    }
}
