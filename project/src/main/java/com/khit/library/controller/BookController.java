package com.khit.library.controller;

import com.khit.library.config.SecurityUser;
import com.khit.library.dto.BookDTO;
import com.khit.library.dto.MemberDTO;
import com.khit.library.service.BookService;
import com.khit.library.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;
    private final MemberService memberService;


    //책등록 폼
    @GetMapping("/register")
    public String insertFrom(){
        return "book/register";
    }
    //책 등록 처리
    @PostMapping("/register")
    public String insert(@ModelAttribute BookDTO bookDTO, MultipartFile bookFile) throws Exception{
        bookService.save(bookDTO, bookFile);
        return "redirect:/";
    }
    //책 리스트
    @GetMapping("/list")
    public String getList(Model model) {
        List<BookDTO> bookDTOList = bookService.findAll();
        model.addAttribute("bookList", bookDTOList);
        return "book/list";
    }
    //책 상세
    @GetMapping("/detail/{bookId}")
    public String detail(@PathVariable Long bookId, Model model, @AuthenticationPrincipal SecurityUser principal){
        BookDTO bookDTO = bookService.findById(bookId);
        model.addAttribute("book", bookDTO);
        if(principal == null){
            return "book/detail";
        }else{
            MemberDTO memberDTO = memberService.findByMid(principal);
            model.addAttribute("member", memberDTO);
            return "book/detail";
        }
    }
    //책 수정
    @GetMapping("/update/{bookId}")
    public String updateForm(@PathVariable Long bookId, Model model){
        BookDTO bookDTO = bookService.findById(bookId);
        model.addAttribute("book", bookDTO);
        return "book/update";
    }
    //책 수정 처리
    @PostMapping("/update")
    public String update(@ModelAttribute BookDTO bookDTO){
        bookService.update(bookDTO);
        return "redirect:/book/list";
    }
    //책 삭제
    @GetMapping("/delete/{bookId}")
    public String delete(@PathVariable Long bookId){
        bookService.deleteById(bookId);
        return "redirect:/book/list";
    }
    //책 검색
    @GetMapping("/search")
    public String search(@RequestParam String keyword, Model model) {
        List<BookDTO> searchResults = bookService.search(keyword);
        model.addAttribute("searchResults", searchResults);
        return "book/searchResults";
    }

}