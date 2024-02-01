package com.khit.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
    @GetMapping("/auth/main")
    public String main(){
        return "/auth/main";
    }

    //권한 에러 페이지
    @GetMapping("/auth/accessDenied")
    public String access(){
        return "auth/accessDenied";
    }
}
