package com.khit.study.repository;

import java.sql.Timestamp;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.khit.study.entity.Board;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@SpringBootTest
public class QueryMethodTest {
	@Autowired
	private BoardRepository boardRepository;
	
	//테스트 데이터 생성(200개)
//	@BeforeEach
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
	/*
	@Test
	public void testFindByTitleContainingOrContentContaining() {
		List<Board> boardList = boardRepository.findByTitleContainingOrContentContaining("대상현", "100");
		
		boardList.forEach(board->log.info(board.toString()));
	}*/
	/*
	@Test
	public void testFindByTitleContainingOrderByIdDesc() {
		List<Board> boardList = boardRepository.findByTitleContainingOrderByIdDesc("15");
		for(Board board : boardList) {
			log.info(board.toString());
		}
	}*/
	/*
	@Test
	public void tsetFindByTitleContaining() {
		//0 -> 1페이지
//		Pageable paging = PageRequest.of(0, 10);
		Pageable paging = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "id"));
		
		log.info("page : " + paging.getPageNumber()); //페이지 번호
		log.info("pagesize: " + paging.getPageSize()); //페이지 글 개수
				
		List<Board> boardList = boardRepository.findByTitleContaining("제목", paging);
		
		boardList.forEach(board -> log.info(board.toString()));
	}*/
	
	@Test
	public void testJpaPaging() {
		Pageable paging = PageRequest.of(0, 10, Sort.Direction.DESC, "id");
		
		Page<Board> pageInfo = boardRepository.findByTitleContaining("제목", paging);
		log.info("페이지 번호 = " +  pageInfo.getNumber());
		log.info("페이지당 게시글 수 = " +  pageInfo.getSize());
		log.info("게시글 총개수 = " +  pageInfo.getTotalElements());
		log.info("게시글 총 페이지수 " +  pageInfo.getTotalPages());
		
		List<Board> boardList = pageInfo.getContent();
		
		for(Board board : boardList) {
			log.info(board.toString());
		}
	}
	
}
