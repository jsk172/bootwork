package com.khit.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.khit.library.entity.FreeBoard;
import com.khit.library.service.FreeBoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class FreeBoardController {
	private final FreeBoardService fboardService;
	
	//글쓰기
	@GetMapping("/freeboard/write")
	public String writeForm() {
		return "board/write";
	}
	@PostMapping("/freeboard/write")
	public String write(@ModelAttribute FreeBoard fboard) {
		fboardService.insert(fboard);
		return "redirect:/";
	}
	@GetMapping("/freeboard/update")
	public String updateForm() {
		return "freeboard/update";
	}
	// 글 수정 처리
    @PostMapping("/freeboard/update")
    public String update() {
    	return "redirect:/freeboard/"; // hopeboardDTO.getId();
    }
    
    // 글 전체 목록
    @GetMapping("/freeboard/pagelist")
    public String getAllList() {
    	return "freeboard/pagelist";
    }
    
    // 글 하나 상세보기
    @GetMapping("/freeboard/detail")
    public String getDetail() {
    	return "freeboard/detail";
    }
}










