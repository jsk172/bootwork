package com.khit.study.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.khit.study.entity.Board;
import com.khit.study.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@Controller
public class BoardController {
	
	private BoardService boardService;
	//글쓰기 페이지
	@GetMapping("/board/write")
	public String writeForm() {
		return "/board/write";
	}
	@PostMapping("/board/write")
	public String write(@ModelAttribute Board board) {
		boardService.save(board);
		log.info("board: " + board);
		return "redirect:/board/";
	}
	
	//글목록
	@GetMapping("/board/")
	public String getBoardList(Model model) {
		List<Board> boardList = boardService.findAll();
		model.addAttribute("boardList", boardList);
		return "/board/list";
	}
	//글상세보기
	@GetMapping("/board/detail")
	public String getBoard(@RequestParam("id") Long id, Model model) {
		Board board = boardService.findById(id);
		model.addAttribute("board", board);
		return "/board/detail";
	}
	//글 삭제
	@GetMapping("/board/delete")
	public String delete(@RequestParam("id") Long id) {
		boardService.delete(id);
		return "redirect:/board/";
	}
	//글 수정
	@GetMapping("/board/update")
	public String updateForm(Model model, @RequestParam("id")Long id) {
		//해당 게시글 가져오기
		Board board = boardService.findById(id);
		//페이지에 모델 전송
		model.addAttribute("board", board);
		return "/board/update";
	}
	@PostMapping("/board/update")
	public String update(@ModelAttribute Board board) {
		boardService.update(board);
		return "redirect:/board/";
	}
}