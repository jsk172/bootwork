package com.khit.study.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.khit.study.entity.BoardVO;
import com.khit.study.service.BoardService;

import lombok.AllArgsConstructor;

//@AllArgsConstructor //생성자 주입
@RestController //문자열을 반환하는 어노테이션
//@ResponseBody, @ResponseEntity와 비슷함
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping("/greeting")
	public String sayHello(String name) {
		return "hello " + name; //문자열
	}
	
	
	//객체 데이터를 브라우저에 반환
	@GetMapping("/board/detail")
	public BoardVO getBoard() {
		BoardVO board = boardService.getBoard();
		return board;
	}
	
	@GetMapping("/board/list")
	public List<BoardVO> getList(){
		List<BoardVO> boardList = boardService.getBoardList();
		return boardList;
	}
}
