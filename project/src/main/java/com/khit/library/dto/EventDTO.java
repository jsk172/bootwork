package com.khit.library.dto;

import java.sql.Timestamp;

import com.khit.library.entity.Event;
import com.khit.library.entity.Member;
import com.khit.library.entity.NoticeBoard;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EventDTO {
	
private Long evid;	//이벤트 글 번호
	
	private String evtitle; //이벤트 제목
	
	@NotBlank(message = "공지사항 내용은 비워둘 수 없습니다.")
	private String evcontent;	//이벤트 내용
	
	private Timestamp createdDate;	//작성일
	private Timestamp updatedDate;	//수정일
	
	private Integer evhit;	//조회수
	
	private String eventFilename;
	private String eventFilepath;
	
	private Member member;	//작성자 - 외래
	
	//entity -> dto
		public static EventDTO toSaveDTO(Event event) {
			EventDTO eventDTO = EventDTO.builder().evid(event.getEvid())
																	.member(event.getMember())
																	.evtitle(event.getEvtitle())
																	.evcontent(event.getEvcontent())
																	.createdDate(event.getCreatedDate())
																	.updatedDate(event.getUpdatedDate())
																	.evhit(event.getEvhit())
																	.eventFilename(event.getEventFilename())
																	.eventFilepath(event.getEventFilepath())
																	.build();
			return eventDTO;
		}
}
