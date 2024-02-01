package com.khit.library.controller;

import com.khit.library.entity.Board;
import com.khit.library.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    //글쓰기
    @GetMapping("/board/write")
    public String writeForm(){
        return "board/write";
    }
    @PostMapping("/board/write")
    public String write(@ModelAttribute Board board){
        boardService.insert(board);
        return "redirect:/";
    }
}
