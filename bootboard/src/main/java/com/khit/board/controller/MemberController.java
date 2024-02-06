package com.khit.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khit.board.dto.MemberDTO;
import com.khit.board.entity.Member;
import com.khit.board.service.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MemberController {
	
	private final MemberService memberService;
	//회원가입
	@GetMapping("/member/join")
	public String joinForm() {
		return "member/join";
	}
	@PostMapping("member/join")
	public String join(@ModelAttribute MemberDTO memberDTO) {
		memberService.save(memberDTO);
		System.out.println("memberDTO: " + memberDTO);
		return "redirect:/";
	}
	
	//회원목록
	@GetMapping("/member/list")
	public String list(Model model) {
		//memberDTO로 반환할 것
		List<MemberDTO> memberDTOList = memberService.findAll();
		model.addAttribute("memberList", memberDTOList);
		return "member/list";
	}
	//회원 상세보기
	//@PathVariable - 경로에 변수를 할당
	@GetMapping("/member/{id}")
	public String detail(@PathVariable Long id, Model model) {
		MemberDTO memberDTO = memberService.findById(id);
		model.addAttribute("member", memberDTO);
		return "member/detail";
	}
	//회원 삭제
	@GetMapping("/member/delete/{id}")
	public String delete(@PathVariable Long id) {
		memberService.deleteById(id);
		return "redirect:/member/list";
	}
	
	
	
	//로그인
	@GetMapping("/member/login")
	public String loginForm() {
		return "/member/login";
	}
	@PostMapping("/member/login")
	public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session, Model model) {
		MemberDTO loginMember = memberService.login(memberDTO);
		//로그인결과(객체가 있으면 로그인됨, 없으면 다시 로그인 폼)
		if(loginMember != null) {
			session.setAttribute("sessionEmail", loginMember.getMemberEmail());
			session.setAttribute("sessionName", loginMember.getMemberName());
			System.out.println("로그인 성공 : " + memberDTO);
			return "main";
		}else {
			System.out.println("로그인 실패");
			String error = "아이디나 비밀번호를 확인해 주세요.";
			model.addAttribute("error", error);
			return "/member/login";
		}
	}
	//로그아웃
	@GetMapping("/member/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	//정보수정
	@GetMapping("/member/update")
	public String updateForm(HttpSession session, Model model) {
		String email = (String)session.getAttribute("sessionEmail");
		MemberDTO memberDTO = memberService.findByEmail(email);
		model.addAttribute("member", memberDTO);
		return "/member/update";
	}
	@PostMapping("/member/update")
	public String update(@ModelAttribute MemberDTO memberDTO) {
		memberService.update(memberDTO);
		return "redirect:/member/list";
	}
	
	//이메일 중복검사
	@PostMapping("/member/check-email")
	public @ResponseBody String checkEmail(@RequestParam("memberEmail") String memberEmail) {
		String resultText = memberService.checkEmail(memberEmail);

		return resultText;
	}
}
