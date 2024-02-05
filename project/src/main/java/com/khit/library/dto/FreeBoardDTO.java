package com.khit.library.dto;

import java.sql.Timestamp;

import com.khit.library.entity.FreeBoard;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FreeBoardDTO {
	
	private Long fbid;  //자유게시판 번호
	
	private String fbtitle;  //자유게시판 제목
	
	private String fbcontent; //자유게시판 내용
	private Integer fbhit;  //조회수
	
	private Timestamp createdDate;
	private Timestamp updatedDate;
	
	//entity -> dto
	public static FreeBoardDTO toSaveDTO(FreeBoard freeBoard) {
		FreeBoardDTO freeBoardDTO = FreeBoardDTO.builder()
				.fbid(freeBoard.getFbid())
				.fbtitle(freeBoard.getFbtitle())
				.fbcontent(freeBoard.getFbcontent())
				.fbhit(freeBoard.getFbhit())
				.createdDate(freeBoard.getCreatedDate())
				.updatedDate(freeBoard.getUpdatedDate())
				.build();
		
		return freeBoardDTO;
	}
	
}
