package com.khit.board.entity;

import com.khit.board.dto.MemberDTO;

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

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tbl_member")
@Entity
@Data
public class Member {
	@Id //primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //자동순번
	private Long id;
	@Column(unique = true)
	private String memberEmail;
	@Column(nullable=false) //length를 주지 않으면 기본적으로 255
	private String memberPassword;
	@Column(length=30, nullable=false)
	private String memberName;
	@Column(nullable=false)
	private int memberAge;
	
	//DTO를 매개변수로 받아서 entity에 저장하는 정적 매서드 생성
	//회원가입
	public static Member toSaveEntity(MemberDTO memberDTO) {
		/*
		Member member = new Member();
		member.setMemberEmail(memberDTO.getMemberEmail());
		member.setMemberPassword(memberDTO.getMemberPassword());
		member.setMemberName(memberDTO.getMemberName());
		member.setMemberAge(memberDTO.getMemberAge());
		*/
		Member member = Member.builder()
				.memberEmail(memberDTO.getMemberEmail())
				.memberPassword(memberDTO.getMemberPassword())
				.memberName(memberDTO.getMemberName())
				.memberAge(memberDTO.getMemberAge())
				.build();
		
		return member;
	}
	//수정을 위한 정적 매서드
	public static Member toUpdateEntity(MemberDTO memberDTO) {
		
		Member member = new Member();
		member.setId(memberDTO.getId());
		member.setMemberEmail(memberDTO.getMemberEmail());
		member.setMemberPassword(memberDTO.getMemberPassword());
		member.setMemberName(memberDTO.getMemberName());
		member.setMemberAge(memberDTO.getMemberAge());
		
		return member;
	}
}
