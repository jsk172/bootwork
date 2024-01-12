package com.khit.study.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;

import com.khit.study.entity.BoardVO;

import lombok.AllArgsConstructor;

@Controller
public class BoardService {
	
	
	//상세보기
	public BoardVO getBoard() {
		BoardVO board = new BoardVO();
		board.setId(1);
		board.setTitle("대상현의 코딩");
		board.setWriter("빛상현");
		board.setContent("KH대상현의 코딩강의 입니다.");
		board.setCreateDate(new Date());
		
		return board;
	}
	//리스트 보기
	public List<BoardVO> getBoardList(){
		List<BoardVO> boardList = new ArrayList<>();
		for(int i=1; i<=10; i++) {
			BoardVO board = new BoardVO();
			board.setId(i);
			board.setTitle("대상현의 코딩" + i);
			board.setWriter("빛상현");
			board.setContent(i + "번 KH대상현의 코딩강의 입니다.");
			board.setCreateDate(new Date());
			
			boardList.add(board);
		}
		return boardList;
	}
}
