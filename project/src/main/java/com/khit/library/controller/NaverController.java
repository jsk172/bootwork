//package com.khit.library.controller;
//
//import com.khit.library.entity.Member;
//import com.khit.library.service.NaverService;
//import jakarta.servlet.http.HttpServletRequest;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/naver")
//public class NaverController {
//    private final NaverService naverService;
//
//    @GetMapping("/callback")
//    public ResponseEntity<Member> callback(HttpServletRequest request) throws Exception{
//        NaverDTO naverinfo = naverService.getNaverInfo(request.getParameter("code"));
//
//        return ResponseEntity.ok().body(new Member("success", naverinfo));
//    }
//}
