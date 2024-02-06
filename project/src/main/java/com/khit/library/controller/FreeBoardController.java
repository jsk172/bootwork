package com.khit.library.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.khit.library.config.SecurityUser;
import com.khit.library.dto.FreeBoardDTO;
import com.khit.library.entity.FreeBoard;
import com.khit.library.service.FreeBoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class FreeBoardController {
	private final FreeBoardService freeBoardService;
	
	// write page 글쓰기
    @GetMapping("/freeboard/write")
    public String writeForm() {
        return "freeboard/write";
    }
    
    // 글쓰기 처리
    @PostMapping("/freeboard/write")
    public String write(@ModelAttribute FreeBoard freeBoard,
    				    @AuthenticationPrincipal SecurityUser principal) {
//    	freeBoard.setMember(principal.getMember());
    	freeBoard.setFbhit(0);
    	freeBoardService.save(freeBoard);
    	return "redirect:/freeboard/pagelist";
    }

    // update page 글 수정
    @GetMapping("/freeboard/update/{fbid}")
    public String updateForm(@PathVariable Long fbid, Model model) {
    	FreeBoardDTO freeBoardDTO = freeBoardService.findById(fbid);
    	model.addAttribute("freeBoard", freeBoardDTO);
    	return "freeboard/update";
    }
    
    // 글 수정 처리
    @PostMapping("/freeboard/update/{fbid}")
    public String update(@ModelAttribute FreeBoardDTO freeBoardDTO) {
    	freeBoardService.update(freeBoardDTO);
    	return "redirect:/freeboard/" + freeBoardDTO.getFbid();
    }
    
    // 글 전체 목록
    @GetMapping("/freeboard/pagelist")
    public String getAllList(Model model) {
    	List<FreeBoardDTO> freeBoardDTOList = freeBoardService.findAll();
    	model.addAttribute("freeBoardList", freeBoardDTOList);
    	return "freeboard/pagelist";
    }
    
    // 글 하나 상세보기
    @GetMapping("/freeboard/{fbid}")
    public String getDetail(@PathVariable Long fbid, Model model) {
    	FreeBoardDTO freeBoardDTO = freeBoardService.findById(fbid);
    	model.addAttribute("freeBoard", freeBoardDTO);
    	return "freeboard/detail";
    }
    
    // 글 삭제
    @GetMapping("/freeboard/delete/{fbid}")
    public String deleteFreeBoard(@PathVariable Long fbid) {
    	freeBoardService.deleteById(fbid);
    	return "redirect:/freeboard/pagelist";
    }
    // 글 검색
    @GetMapping("/freeboard/search")
    public String search(@RequestParam String keyword, Pageable pageable, Model model) {
        Page<FreeBoardDTO> searchResults = freeBoardService.search(keyword, pageable);
        model.addAttribute("freeBoardList", searchResults);
        return "freeboard/pagelist";
    }
}










