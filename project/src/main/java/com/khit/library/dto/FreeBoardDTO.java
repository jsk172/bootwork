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
	
	public static FreeBoardDTO toSaveDTO(FreeBoard fboard) {
		FreeBoardDTO fbDTO = FreeBoardDTO.builder()
				.fbid(fboard.getFbid())
				.fbtitle(fboard.getFbtitle())
				.fbcontent(fboard.getFbcontent())
				.fbhit(fboard.getFbhit())
				.createdDate(fboard.getCreatedDate())
				.updatedDate(fboard.getUpdatedDate())
				.build();
		
		return fbDTO;
	}
	
}
