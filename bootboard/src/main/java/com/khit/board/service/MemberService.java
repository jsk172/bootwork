package com.khit.board.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.khit.board.dto.MemberDTO;
import com.khit.board.entity.Member;
import com.khit.board.exception.BootBoardException;
import com.khit.board.repository.MemberRepository;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //생성자 주입 방식 - final 꼭 붙임
public class MemberService {
	
	private final MemberRepository memberRepository;

	public void save(MemberDTO memberDTO) {
		//db안으로 저장(entity를 저장해야함)
		//dto를 entity로 변환 매서드 필요
		Member member = Member.toSaveEntity(memberDTO);
		memberRepository.save(member);
	}

	public List<MemberDTO> findAll() {
		//DB에서 member엔티티를 꺼내와서 컨트롤러에 DTO로 보냄
		List<Member> memberList = memberRepository.findAll();
		//변환 매서드
		//member를 담을 빈 DTO리스트 생성
		List<MemberDTO> memberDTOList =  new ArrayList<>();
		for(Member member : memberList) {
			MemberDTO memberDTO = MemberDTO.toSaveDTO(member);
			memberDTOList.add(memberDTO);
		}
		
		//컨트롤러에 DTOList로 보냄
		return memberDTOList;
	}

	public MemberDTO findById(Long id) {
		//db에서 member 1개 꺼내오기 .get() 사용
		Optional<Member> member = memberRepository.findById(id);
		if(member.isPresent()) {
			//entity -> dto 변환
			MemberDTO memberDTO = MemberDTO.toSaveDTO(member.get());
			return memberDTO;
		}else {
			throw new BootBoardException("찾는 데이터가 없습니다.");
		}
	}

	public void deleteById(Long id) {
		memberRepository.deleteById(id);
	}

	public MemberDTO login(MemberDTO memberDTO) {
		//이메일로 회원 조회(이메일과 비밀번호 비교)
		Optional<Member> memberEmail = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
		if(memberEmail.isPresent()) {
			//조회 결과가 있으면 - 1건 가져오기
			Member member = memberEmail.get();
			//비밀번호 일치/불일치
			if(member.getMemberPassword().equals(memberDTO.getMemberPassword())) {
				//비밀번호 일치
				//변환 필요(entity -> dto)
				MemberDTO dto = MemberDTO.toSaveDTO(member);
				return dto;
			}else {
				//비밀번호 불일치
				return null;
			}
		}else {
			return null;
		}
	}

	public MemberDTO findByEmail(String email) {
		//db에서 이메일로 검색한 회원 객체 가져오기
		Member member = memberRepository.findByMemberEmail(email).get();
		//회원 객체(엔티티)를 dto로 변환
		MemberDTO memberDTO = MemberDTO.toSaveDTO(member);
		return memberDTO;
	}

	public void update(MemberDTO memberDTO) {
		//save가 가입, 수정되는데 가입할때는 id가 없음, 수정할때는 id가 있음
		Member member = Member.toUpdateEntity(memberDTO);
		//아이디가 있는 엔티티의 매서드가 필요함
		memberRepository.save(member);
	}

	public String checkEmail(String memberEmail) {
		//db에있는 이메일을 조회해서 있으면 "ok" 아니면 "no"를 보냄
		Optional<Member> findMember = memberRepository.findByMemberEmail(memberEmail);
		if(findMember.isEmpty()) {
			return "OK";
		}else {
			return "NO";
		}
	}

	
}
