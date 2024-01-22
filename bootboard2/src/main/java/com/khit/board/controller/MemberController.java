package com.khit.board.controller;

import com.khit.board.entity.Member;
import com.khit.board.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    //메인페이지
    @GetMapping("/main")
    public String main(){
        return "main";
    }

    //로그인
    @GetMapping("/member/login")
    public String loginForm(){
        return "/member/login";
    }
    @PostMapping("/member/login")
    public String login(@ModelAttribute Member member, HttpSession session){
        Member loginMember = memberService.login(member);
        if(loginMember != null && loginMember.getPassword().equals(member.getPassword())){
            //아이디와 비밀번호 일치해서 로그인 되면 세션 발급
            session.setAttribute("sessMemberId", loginMember.getMemberId());
            return "main";
        }else{
            return "/member/login";
        }
    }
    //회원가입
    @GetMapping("/member/join")
    public String joinForm(){
        return "/member/join";
    }
    @PostMapping("/member/join")
    public String join(@ModelAttribute Member member){
        memberService.join(member);
        return "redirect:/";
    }
}
