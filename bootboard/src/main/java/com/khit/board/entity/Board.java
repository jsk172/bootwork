package com.khit.board.entity;

import com.khit.board.dto.BoardDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "tbl_board")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String boardTitle;
	@Column(length=30, nullable=false)
	private String boardWriter;
	@Column(length=2000, nullable=false)
	private String boardContent;
	@Column
	private Integer boardHits;
	//write.html에서 name값과
	@Column
	private String filename;
	@Column
	private  String filepath;
	
	
	public static Board toSaveEntity(BoardDTO boardDTO) {
		Board board = Board.builder()
				.boardTitle(boardDTO.getBoardTitle())
				.boardWriter(boardDTO.getBoardWriter())
				.boardContent(boardDTO.getBoardContent())
				.filename(boardDTO.getFilename())
				.filepath(boardDTO.getFilepath())
				.boardHits(0)
				.build();
		
		return board;
	}
	
	//첨부파일이 있는경우
	public static Board toUpdateEntity(BoardDTO boardDTO) {
		Board board = Board.builder()
				.id(boardDTO.getId())
				.boardTitle(boardDTO.getBoardTitle())
				.boardWriter(boardDTO.getBoardWriter())
				.boardContent(boardDTO.getBoardContent())
				.boardHits(boardDTO.getBoardHits())
				.filepath(boardDTO.getFilepath())
				.filename(boardDTO.getFilename())
				.build();
		
		return board;
	}

	//첨부파일이 없는경우
//	public static Board toUpdateNoFile(BoardDTO boardDTO) {
//		Board board = Board.builder()
//				.id(boardDTO.getId())
//				.boardTitle(boardDTO.getBoardTitle())
//				.boardWriter(boardDTO.getBoardWriter())
//				.boardContent(boardDTO.getBoardContent())
//				.boardHits(boardDTO.getBoardHits())
//				.build();
//
//		return board;
//	}
}
