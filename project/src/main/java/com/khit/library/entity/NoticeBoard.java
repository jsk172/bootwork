package com.khit.library.entity;

import com.khit.library.dto.NoticeBoardDTO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
@Table(name = "noticeboard")
public class NoticeBoard extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long nbid; // 공지사항 글 번

	@Column(nullable = false)
	private String nbtitle; // 자유게시판 제목

	@Column(length = 2000, nullable = false)
	private String nbcontent; // 자유게시판 내용

	@Column(nullable = true)
	private Integer nbhit; // 조회수
	
	@ManyToOne(fetch = FetchType.LAZY)	//글쓴이 - 외래
	@JoinColumn(name = "mid")
	private Member member;

	// dto -> entity
	//insert
	public static NoticeBoard toSaveEntity(NoticeBoardDTO noticeBoardDTO) {
		NoticeBoard noticeBoard = NoticeBoard.builder().nbid(noticeBoardDTO.getNbid())
				.nbtitle(noticeBoardDTO.getNbtitle()).nbcontent(noticeBoardDTO.getNbcontent())
				.nbhit(noticeBoardDTO.getNbhit())
				.member(noticeBoardDTO.getMember())
				.build();

		return noticeBoard;
	}
	
	//update
	public static NoticeBoard toUpdateEntity(NoticeBoardDTO noticeBoardDTO) {
		NoticeBoard noticeBoard = NoticeBoard.builder().nbid(noticeBoardDTO.getNbid())
				.nbtitle(noticeBoardDTO.getNbtitle()).nbcontent(noticeBoardDTO.getNbcontent())
				.nbhit(noticeBoardDTO.getNbhit())
				.member(noticeBoardDTO.getMember())
				.build();

		return noticeBoard;
	}
}
