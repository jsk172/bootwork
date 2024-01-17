package com.khit.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
		return "redirect:/board/list";
	}
	//글목록
	@GetMapping("/board/list")
	public String list(Model model) {
		List<BoardDTO> boardDTOList = boardService.findAll();
		model.addAttribute("boardList", boardDTOList);
		return "/board/list";
	}
	//글 상세보기
	@GetMapping("/board/detail/{id}")
	public String detail(@PathVariable Long id, Model model) {
		//조회수
		boardService.updateHits(id);
		//상세보기
		BoardDTO boardDTO = boardService.findById(id);
		model.addAttribute("board", boardDTO);
		return "/board/detail";
	}
	//글 삭제
	@GetMapping("/board/delete/{id}")
	public String delete(@PathVariable Long id) {
		boardService.deleteById(id);
		return "redirect:/board/list";
	}
	//글 수정
	@GetMapping("/board/update/{id}")
	public String updateForm(@PathVariable Long id, Model model) {
		BoardDTO boardDTO = boardService.findById(id);
		model.addAttribute("board", boardDTO);
		return "/board/update";
	}
	@PostMapping("/board/update")
	public String update(@ModelAttribute BoardDTO boardDTO) {
		boardService.update(boardDTO);
		return "redirect:/board/detail/" + boardDTO.getId();
	}
}
