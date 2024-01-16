package com.khit.study.repository;

import java.sql.Timestamp;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.khit.study.entity.Board;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class BoardRepositoryTest {
	@Autowired
	private BoardRepository boardRepository;
	
	//게시글 생성
//	@Test
//	public void insertBoard() {
//		/*
//		Board board = new Board();
//		board.setTitle("대상현의 코딩");
//		board.setWriter("대상현");
//		board.setContent("24/02/20까지 진행");
//		board.setCreateDate(new Timestamp(System.currentTimeMillis()));
//		*/
//		/*Board board = Board.builder()
//				.title("대상현의 코딩3")
//				.writer("대상현")
//				.content("내용준비중")
//				.createDate(new Timestamp(System.currentTimeMillis()))
//				.build();
//		*/
//		//db에 저장
////		boardRepository.save(board);
//	}
//	@Test
//	public void getBoardList() {
//		List<Board> boardList = boardRepository.findAll();
//		//boardList 출력
//		/*
//		for(int i=0; i<boardList.size(); i++) {
//			log.info("대상현의 코딩: " + board.toString());
//		}*/
//		/*
//		for(Board board : boardList) {
//			log.info(board.toString());
//		}*/
//		
//		//람다식
//		boardList.forEach(board -> log.info(board.toString()));
//	}
	
	//1건 상세보기
//	@Test
//	public void getBoard() {
//		//findById()와 get()을 사용
//		Board board = boardRepository.findById(1L).get();
//		log.info(board.toString());
//	}
	//수정하기
//	@Test
//	public void updateBoard() {
//		//수정하려는 게시글을 가져와서(findById) 수정처리(save)
//		Board board = boardRepository.findById(1L).get();
//		board.setTitle("대상현의 코딩1");
//		
//		boardRepository.save(board);
//	}
	//삭제하기
//	@Test
//	public void deleteBoard() {
//		boardRepository.deleteById(3L);
//	}
	@Test
	public void select() {
		List<Board> board = boardRepository.findAll();
		log.info("설하의 검색 : " + board.toString());
	}
	@Test
	public void detail() {
		Board board = boardRepository.findById(3L).get();
	}
}
