/*
package com.khit.library.controller;

import com.khit.library.service.NaverService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class LoginController {
    private final NaverService naverService;

    @GetMapping("/naverLogin")
    public String socialLogin(Model model){
        model.addAttribute("naverUrl", naverService.getNaverLogin());

        return "/";
    }
}
*/
