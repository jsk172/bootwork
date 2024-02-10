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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.khit.library.config.SecurityUser;
import com.khit.library.dto.FreeBoardDTO;
import com.khit.library.dto.MemberDTO;
import com.khit.library.entity.FreeBoard;
import com.khit.library.service.FreeBoardService;
import com.khit.library.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/freeboard")
public class FreeBoardController {
	private final FreeBoardService freeBoardService;
	private final MemberService memberService;

	// write page 글쓰기
	@GetMapping("/write")
	public String writeForm(@AuthenticationPrincipal SecurityUser principal, Model model) {
        if(principal == null){
        	return "freeboard/write";
        }else{
            MemberDTO memberDTO = memberService.findByMid(principal);
            model.addAttribute("member", memberDTO);
            return "freeboard/write";
        }
	}

	// 글쓰기 처리
	@PostMapping("/write")
	public String write(@ModelAttribute FreeBoard freeBoard, @AuthenticationPrincipal SecurityUser principal) {
    	freeBoard.setMember(principal.getMember());
		freeBoard.setFbhit(0);
		freeBoardService.save(freeBoard);
		return "redirect:/freeboard/pagelist";
	}

	// update page 글 수정
	@GetMapping("/update/{fbid}")
	public String updateForm(@PathVariable Long fbid, @AuthenticationPrincipal SecurityUser principal, Model model) {
		FreeBoardDTO freeBoardDTO = freeBoardService.findById(fbid);
		model.addAttribute("freeBoard", freeBoardDTO);
        if(principal == null){
        	return "freeboard/update";
        }else{
            MemberDTO memberDTO = memberService.findByMid(principal);
            model.addAttribute("member", memberDTO);
            return "freeboard/update";
        }
	}

	// 글 수정 처리
	@PostMapping("/update/{fbid}")
	public String update(@ModelAttribute FreeBoardDTO freeBoardDTO) {
		freeBoardService.update(freeBoardDTO);
		return "redirect:/freeboard/" + freeBoardDTO.getFbid();
	}

	// 글 전체 목록
	@GetMapping("/pagelist")
	public String getAllList(Model model, @AuthenticationPrincipal SecurityUser principal) {
		List<FreeBoardDTO> freeBoardDTOList = freeBoardService.findAll();
		model.addAttribute("freeBoardList", freeBoardDTOList);
        if(principal == null){
            return "freeboard/pagelist";
        }else{
            MemberDTO memberDTO = memberService.findByMid(principal);
            model.addAttribute("member", memberDTO);
            return "freeboard/pagelist";
        }
	}

	// 글 하나 상세보기
	@GetMapping("/{fbid}")
	public String getDetail(@AuthenticationPrincipal SecurityUser principal, @PathVariable Long fbid, Model model) {
		FreeBoardDTO freeBoardDTO = freeBoardService.findById(fbid);
		model.addAttribute("freeBoard", freeBoardDTO);
		if(principal == null){
			return "freeboard/detail";
		}else{
			MemberDTO memberDTO = memberService.findByMid(principal);
			model.addAttribute("member", memberDTO);
			return "freeboard/detail";
		}
	}

	// 글 삭제
	@GetMapping("/delete/{fbid}")
	public String deleteFreeBoard(@PathVariable Long fbid) {
		freeBoardService.deleteById(fbid);
		return "redirect:/freeboard/pagelist";
	}

	@GetMapping("/search")
	public String search(@RequestParam(required = false) String searchOption,
	                     @RequestParam(required = false) String keyword,
	                     Pageable pageable,
	                     Model model) {
	    Page<FreeBoardDTO> searchResults;
	    if (searchOption != null && keyword != null) {
	        if ("title".equals(searchOption)) {
	            searchResults = freeBoardService.searchByTitle(keyword, pageable);
	        } else if ("author".equals(searchOption)) {
	            searchResults = freeBoardService.searchByAuthor(keyword, pageable);
	        } else {
	            // 다른 경우에 대한 처리 (예: 기본적으로는 제목으로 검색)
	            searchResults = freeBoardService.search(keyword, pageable);
	        }
	    } else {
	        // searchOption 또는 keyword가 누락된 경우에 대한 처리
	        // 예: 기본적으로는 제목으로 검색하거나 다른 적절한 기본 동작을 설정
	        searchResults = freeBoardService.search(keyword, pageable);
	    }

	    model.addAttribute("freeBoardList", searchResults);
	    return "freeboard/pagelist";
	}
}
