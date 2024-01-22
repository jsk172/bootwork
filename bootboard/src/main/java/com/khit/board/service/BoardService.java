package com.khit.board.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.khit.board.dto.BoardDTO;
import com.khit.board.entity.Board;
import com.khit.board.exception.BootBoardException;
import com.khit.board.repository.BoardRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardService {

	private final BoardRepository boardRepository;

	public void save(BoardDTO boardDTO, MultipartFile boardFile) throws Exception {
		//1. 파일을 서버에 저장하고,
		if(!boardFile.isEmpty()){ //전달된 파일이 있으면
			//저장 경로
			String filepath = "C:\\bootworks\\bootboard\\src\\main\\resources\\static\\upload\\";
			UUID uuid = UUID.randomUUID(); //무작위 아이디 생성
			String filename = uuid + "_" + boardFile.getOriginalFilename(); //원본 파일
			
			//file 클래스 객체 생성
			File savedFile = new File(filepath, filename);//upload 폴더에 저장
			boardFile.transferTo(savedFile);
			// 2. 파일 이름은 db에 저장
			boardDTO.setFilename(filename);
			boardDTO.setFilepath("/upload/" + filename);
		}



		//dto -> entity로 변환
		Board board = Board.toSaveEntity(boardDTO);
		boardRepository.save(board);
	}

	public List<BoardDTO> findAll() {
		//db에서 entity list를 가져옴
		//List<Board> boardList = boardRepository.findAll();
		//내림차순 정렬 - Sort.by(정렬방식, 해당필드)
		List<Board> boardList =
				boardRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
		//비어있는 DTO리스트 생성
		List<BoardDTO> boardDTOList = new ArrayList<>();
		for(Board board : boardList) {
			//entity -> dto로 변환
			BoardDTO boardDTO = BoardDTO.toSaveDTO(board);
			boardDTOList.add(boardDTO);
		}
		return boardDTOList;
	}

	public BoardDTO findById(Long id) {
		Optional<Board> findboard = boardRepository.findById(id);
		if(findboard.isPresent()) {   //검색한 게시글이 있으면 dto를 컨트롤러로 반환
			BoardDTO boardDTO = BoardDTO.toSaveDTO(findboard.get());
			return boardDTO;
		}else {   //게시글이 없으면 에러 처리
			throw new BootBoardException("게시글을 찾을 수 없습니다.");
		}
	}

	@Transactional   //컨트롤러 작업(메서드)이 2개 이상일경우 사용함
	public void updateHits(Long id) {
		//boardRepository에 생성
		boardRepository.updateHits(id);
	}

	public void deleteById(Long id) {
		boardRepository.deleteById(id);
	}

	@Transactional
	public void update(BoardDTO boardDTO, MultipartFile boardFile) throws Exception {
		//1. 파일을 서버에 저장하고,
		if(!boardFile.isEmpty()){ //전달된 파일이 있으면
			//저장 경로
			String filepath = "C:\\bootworks\\bootboard\\src\\main\\resources\\static\\upload\\";
			UUID uuid = UUID.randomUUID(); //무작위 아이디 생성
			String filename = uuid + "_" + boardFile.getOriginalFilename(); //원본 파일

			//file 클래스 객체 생성
			File savedFile = new File(filepath, filename);//upload 폴더에 저장
			boardFile.transferTo(savedFile);
			// 2. 파일 이름은 db에 저장
			boardDTO.setFilename(filename);
			boardDTO.setFilepath("/upload/" + filename);

		}else{
//			Board board = Board.toUpdateNoFile(boardDTO);
//			boardRepository.save(board);
			boardDTO.setFilepath(findById(boardDTO.getId()).getFilepath());
		}
		Board board = Board.toUpdateEntity(boardDTO);
		boardRepository.save(board);
		//save() - 삽입(id가 없고), 수정(id가 있음)
		//dto -> entity 변환
	}

	public Page<BoardDTO> findListAll(Pageable pageable) {

		int page = pageable.getPageNumber() - 1;   //db가 1작음
		int pageSize = 10;
		pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "id");

		Page<Board> boardList = boardRepository.findAll(pageable);

		log.info("boardList.isFirst()= " + boardList.isFirst());
		log.info("boardList.isLast()= " + boardList.isLast());
		log.info("boardList.getNumber()= " + boardList.getNumber());

		//생성자 방식으로 boardDTOList 만들기
		Page<BoardDTO> boardDTOList = boardList.map(board -> new BoardDTO(board.getId(), board.getBoardTitle(),
				board.getBoardWriter(), board.getBoardContent(), board.getBoardHits(), board.getFilename(), board.getFilepath(), board.getCreatedDate(),
				board.getUpdatedDate()));

		return boardDTOList;
	}

	public Page<BoardDTO> findByBoardTitleContaining(String keyword, Pageable pageable) {

		int page = pageable.getPageNumber() - 1;   //db가 1작음
		int pageSize = 10;
		pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "id");

		Page<Board> boardList = boardRepository.findByBoardTitleContaining(keyword, pageable);

		//생성자 방식으로 boardDTOList 만들기
		Page<BoardDTO> boardDTOList = boardList.map(board -> new BoardDTO(board.getId(), board.getBoardTitle(),
				board.getBoardWriter(), board.getBoardContent(), board.getBoardHits(), board.getFilename(), board.getFilepath(), board.getCreatedDate(),
				board.getUpdatedDate()));

		return boardDTOList;
	}

	public Page<BoardDTO> findByBoardContentContaining(String keyword, Pageable pageable) {

		int page = pageable.getPageNumber() - 1;   //db가 1작음
		int pageSize = 10;
		pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "id");

		Page<Board> boardList = boardRepository.findByBoardContentContaining(keyword, pageable);

		//생성자 방식으로 boardDTOList 만들기
		Page<BoardDTO> boardDTOList = boardList.map(board -> new BoardDTO(board.getId(), board.getBoardTitle(),
				board.getBoardWriter(), board.getBoardContent(), board.getBoardHits(), board.getFilename(), board.getFilepath(), board.getCreatedDate(),
				board.getUpdatedDate()));

		return boardDTOList;
	}
}