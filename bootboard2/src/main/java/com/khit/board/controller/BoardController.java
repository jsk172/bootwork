package com.khit.board.controller;

import com.khit.board.config.SecurityUser;
import com.khit.board.dto.BoardDTO;
import com.khit.board.entity.Board;
import com.khit.board.service.BoardService;
import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    //글목록
    @GetMapping("/list")
    public String getList(Model model){
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList", boardDTOList);
        return "board/list";
    }
    //글 상세보기
    @GetMapping("/{id}")
    public String getBoard(@PathVariable Integer id, Model model){
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board", boardDTO);
        return "board/detail";
    }
    //글쓰기
    @GetMapping("/write")
    public String writeForm(){
        return "board/write";
    }
    @PostMapping("/write")
    public String write(@ModelAttribute BoardDTO boardDTO, @AuthenticationPrincipal SecurityUser principal){
        boardDTO.setMember(principal.getMember()); //
        boardService.save(boardDTO);
        return "redirect:/board/list";
    }
    //게시글 삭제
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        boardService.deleteById(id);
        return "redirect:/board/list";
    }
    //게시글 수정
    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Integer id, Model model){
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board", boardDTO);
        return "board/update";
    }
    @PostMapping("/update")
    public String update(@ModelAttribute BoardDTO boardDTO, @AuthenticationPrincipal SecurityUser principal){
        boardDTO.setMember(principal.getMember());
        boardService.update(boardDTO);
        return "redirect:/board/update/" + boardDTO.getId();
    }
}
