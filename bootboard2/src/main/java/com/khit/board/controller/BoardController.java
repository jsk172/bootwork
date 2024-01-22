package com.khit.board.controller;

import com.khit.board.entity.Board;
import com.khit.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    //회원목록
    @GetMapping("/list")
    public String getList(Model model){
        List<Board> boardList = boardService.findAll();
        model.addAttribute("boardList", boardList);
        return "/board/list";
    }
    //회원 상세보기
    @GetMapping("/{id}")
    public String getBoard(@PathVariable Integer id, Model model){
        Board board = boardService.findById(id);
        model.addAttribute("board", board);
        return "/board/detail";
    }
}
