package com.khit.library.controller;

import com.khit.library.dto.BookDTO;
import com.khit.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;
    
    //책등록 폼
    @GetMapping("/register")
    public String insertFrom(){
        return "book/register";
    }
    //책 등록 처리
    @PostMapping("/register")
    public String insert(@ModelAttribute BookDTO bookDTO){
        bookService.save(bookDTO);
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
    public String detail(@PathVariable Long bookId, Model model){
        BookDTO bookDTO = bookService.findById(bookId);
        model.addAttribute("book", bookDTO);
        return "book/detail";
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

}
