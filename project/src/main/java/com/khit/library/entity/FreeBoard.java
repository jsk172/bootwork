package com.khit.library.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import com.khit.library.dto.FreeBoardDTO;

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

@Entity
@Data
@Table(name = "free_board")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FreeBoard extends BaseEntity{
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long fbid;  //자유게시판 번호
	
	@Column(nullable = false)
	private String fbtitle; //자유게시판 제목
	
	@Column(length = 2000, nullable = false)
	private String fbcontent; //자유게시판 내용
	
	@Column(nullable = true)
	private Integer fbhit;  //조회수
	
	public static FreeBoard toSaveEntity(FreeBoardDTO fboardDTO) {
		FreeBoard fboard = FreeBoard.builder()
				.fbtitle(fboardDTO.getFbtitle())
				.fbcontent(fboardDTO.getFbcontent())
				.fbhit(fboardDTO.getFbhit())
				.build();
		return fboard;
	}
	public static FreeBoard toUpdateEntity(FreeBoardDTO fboardDTO) {
		FreeBoard fboard = FreeBoard.builder()
				.fbid(fboardDTO.getFbid())
				.fbtitle(fboardDTO.getFbtitle())
				.fbcontent(fboardDTO.getFbcontent())
				.fbhit(fboardDTO.getFbhit())
				.build();
		return fboard;
	}

}
