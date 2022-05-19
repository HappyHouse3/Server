package com.ssafy.happyhouse.api.board;

import com.ssafy.happyhouse.dto.BoardInputDto;
import com.ssafy.happyhouse.entity.User;
import com.ssafy.happyhouse.entity.board.Board;
import com.ssafy.happyhouse.entity.board.QnaBoard;
import com.ssafy.happyhouse.entity.board.Reply;
import com.ssafy.happyhouse.dto.BoardDto;
import com.ssafy.happyhouse.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/api/notice")
@RestController
public class NoticeApiController {

    private final NoticeService boardService;

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
        Board board = new QnaBoard();
        return new ResponseEntity<>(boardService.saveBoard(boardDto), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integer> update(@PathVariable Integer id, @RequestBody QnaBoard board) {
        System.out.println("1232board = " + board);
        boardService.updateBoard(id, board);
        return new ResponseEntity<>(1, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> delete(@PathVariable Integer id) {
        boardService.delete(id);
        return new ResponseEntity<>(1, HttpStatus.OK);
    }
}
