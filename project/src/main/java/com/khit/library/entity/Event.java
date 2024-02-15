package com.khit.library.entity;

import com.khit.library.dto.EventDTO;
import com.khit.library.dto.NoticeBoardDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
@Table(name = "event")
public class Event extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long evid; // 이벤트 글 번호

	@Column(nullable = false)
	private String evtitle; // 이벤트 제목

	@Column(length = 2000, nullable = false)
	private String evcontent; // 이벤트 내용

	@Column
	private Integer evhit; // 조회수
	
	@Column
	private String eventFilename;
	@Column
	private String eventFilepath;
	
	@ManyToOne(fetch = FetchType.LAZY)	//글쓴이 - 외래
	@JoinColumn(name = "mid")
	private Member member;

	// dto -> entity
	//insert
	public static Event toSaveEntity(EventDTO eventDTO) {
		Event event = Event.builder().evid(eventDTO.getEvid())
				.evtitle(eventDTO.getEvtitle()).evcontent(eventDTO.getEvcontent())
				.evhit(0)
				.eventFilename(eventDTO.getEventFilename())
				.eventFilepath(eventDTO.getEventFilepath())
				.member(eventDTO.getMember())
				.build();

		return event;
	}
	
	//update
	public static Event toUpdateEntity(EventDTO eventDTO) {
		Event event = Event.builder().evid(eventDTO.getEvid())
				.evtitle(eventDTO.getEvtitle()).evcontent(eventDTO.getEvcontent())
				.evhit(eventDTO.getEvhit())
				.eventFilename(eventDTO.getEventFilename())
				.eventFilepath(eventDTO.getEventFilepath())
				.member(eventDTO.getMember())
				.build();

		return event;
	}
	
	
}
