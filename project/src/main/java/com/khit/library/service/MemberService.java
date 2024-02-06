package com.khit.library.service;

import com.khit.library.config.SecurityUser;
import com.khit.library.dto.MemberDTO;
import com.khit.library.entity.Member;
import com.khit.library.entity.Role;
import com.khit.library.exception.FinalException;
import com.khit.library.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder pwEncoder;

    //회원가입
    public void save(MemberDTO memberDTO) {
        String encPw = pwEncoder.encode(memberDTO.getPassword()); //비밀번호 암호화
        memberDTO.setPassword(encPw);
        memberDTO.setRole(Role.Member); //권한설정
        Member member = Member.toSaveEntity(memberDTO);
        memberRepository.save(member);
    }
    public Member login(Member member){
        Optional<Member> findMember = memberRepository.findByMid(member.getMid());
        if(findMember.isPresent()){
            return findMember.get();
        }else{
            return null;
        }
    }

    public MemberDTO findByMid(SecurityUser principal){
        Optional<Member> member = memberRepository.findByMid(principal.getUsername());
        MemberDTO memberDTO = MemberDTO.toSaveDTO(member.get());
        return memberDTO;
    }
    //회원목록
    public List<MemberDTO> findAll() {
        List<Member> memberList = memberRepository.findAll();
        List<MemberDTO> memberDTOList = new ArrayList<>();

        for(Member member : memberList){
            MemberDTO memberDTO = MemberDTO.toSaveDTO(member);
            memberDTOList.add(memberDTO);
        }
        return memberDTOList;
    }
    //회원 삭제
    public void deleteById(Long memberId) {
        memberRepository.deleteById(memberId);
    }
    //회원 수정
    public void update(MemberDTO memberDTO) {
        String encPw = pwEncoder.encode(memberDTO.getPassword());
        memberDTO.setPassword(encPw);
        memberDTO.setRole(Role.Admin);

        Member member = Member.toUpdateEntity(memberDTO);
        memberRepository.save(member);
    }
    //회원상세보기
    public MemberDTO findById(Long memberId) {
        Optional<Member> findMember = memberRepository.findById(memberId);
        if(findMember.isPresent()){
            MemberDTO memberDTO = MemberDTO.toSaveDTO(findMember.get());
            return memberDTO;
        }else{
            throw new FinalException("페이지를 찾을 수 없습니다");
        }
    }

    public MemberDTO findByUsername(String username) {
        return null;
    }

    //아이디 중복검사
    public String checkId(String mid) {
        Optional<Member> findMember = memberRepository.findByMid(mid);
        if(mid.length() < 6){
            return "len";
        }else if(mid.length() > 15){
            return "len";
        }
        if(findMember.isEmpty()){
            return "OK";
        }else{
            return "NO";
        }
    }
}
