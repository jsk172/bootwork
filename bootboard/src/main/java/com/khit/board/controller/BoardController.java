package com.khit.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.khit.board.dto.BoardDTO;
import com.khit.board.service.BoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardController {
	private final BoardService boardService;
	
	//글쓰기
	@GetMapping("/board/write")
	public String writeForm() {
		return "/board/write";
	}
	@PostMapping("/board/write")
	public String write(@ModelAttribute BoardDTO boardDTO) {
		boardService.save(boardDTO);
		return "/board/list";
	}
	//글목록
	@GetMapping("/board/list")
	public String list(Model model) {
		List<BoardDTO> boardList = boardService.findAll();
		model.addAttribute("boardlist", boardList);
		return "/board/list";
	}
}
