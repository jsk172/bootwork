package com.khit.study.repository;

import java.sql.Timestamp;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.khit.study.entity.Board;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@SpringBootTest
public class QueryMethodTest {
	@Autowired
	private BoardRepository boardRepository;
	
	//테스트 데이터 생성(200개)
	@BeforeEach
	/*public void dataCreate() {
		for(int i=1; i<=200; i++) {
			Board board = new Board();
			board.setTitle("테스트 제목 : " + i);
			board.setContent("테스트 내용 : " + i);
			board.setWriter("테스터 : " + i);
			board.setCreateDate(new Timestamp(System.currentTimeMillis()));
			
			boardRepository.save(board);
		}
	}*/
	/*
	@Test
	public void testFindByTitle() {
		List<Board> boardList = boardRepository.findByTitle("테스트 제목 : 10");
		
		for(Board board : boardList) {
			log.info(board.toString());
		}
	}*/
	/*
	@Test
	public void testFindByTitleContaining() {
		List<Board> boardList = boardRepository.findByTitleContaining("10");
		
		for(Board board : boardList) {
			log.info(board.toString());
		}
	}*/
	@Test
	public void testFindByTitleContainingOrContentContaining() {
		List<Board> boardList = boardRepository.findByTitleContainingOrContentContaining("대상현", "100");
		
		boardList.forEach(board->log.info(board.toString()));
	}
}
