package com.khit.board.dto;

import java.time.LocalDateTime;

import com.khit.board.entity.Board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BoardDTO {
	private Long id;
	private String boardTitle;
	private String boardWriter;
	private String boardContent;
	private Integer boardHits;
	private String filename;
	private String filepath;
	
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	
	//엔티티 -> DTO 변환
	//DB에 있는 모든 칼럼 가져옴
	public static BoardDTO toSaveDTO(Board board) {
		BoardDTO boardDTO = BoardDTO.builder()
				.id(board.getId())
				.boardTitle(board.getBoardTitle())
				.boardWriter(board.getBoardWriter())
				.boardContent(board.getBoardContent())
				.filename(board.getFilename())
				.filepath(board.getFilepath())
				.boardHits(board.getBoardHits())
				.createdDate(board.getCreatedDate())
				.updatedDate(board.getUpdatedDate())
				.build();
		return boardDTO;
	}

}
