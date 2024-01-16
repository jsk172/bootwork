package com.khit.study.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.khit.study.entity.Board;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class Test {
	
	@Autowired
	private BoardRepository boardRepository;
	
	@org.junit.jupiter.api.Test
	public void select() {
		List<Board> board = boardRepository.findAll();
		log.info("설하의 검색 : " + board.toString());
	}
}
