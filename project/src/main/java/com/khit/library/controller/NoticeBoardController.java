package com.khit.library.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.khit.library.config.SecurityUser;
import com.khit.library.dto.HopeBoardDTO;
import com.khit.library.dto.NoticeBoardDTO;
import com.khit.library.entity.NoticeBoard;
import com.khit.library.service.NoticeBoardService;

import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class NoticeBoardController {
	
    private final NoticeBoardService noticeBoardService;

	//쓰기 페이지
	@GetMapping("/notice/write")
	public String writeForm() {
		return "/notice/write";
	}

	//쓰기 처리
	@PostMapping("/notice/write")
	public String write(@ModelAttribute NoticeBoard noticeBoard,
			@AuthenticationPrincipal SecurityUser principal) {
    	noticeBoard.setMember(principal.getMember());
		noticeBoard.setNbhit(0);
		noticeBoardService.save(noticeBoard);
		return "redirect:/";
	}
	
	//수정 페이지
	@GetMapping("/notice/update/{nbid}")
	public String updateForm(@PathVariable Long nbid, Model model) {
		NoticeBoardDTO noticeBoardDTO = noticeBoardService.findById(nbid);
		model.addAttribute("noticeBoard", noticeBoardDTO);
		return "/notice/update";
	}
	
	//수정 처리
	@PostMapping("/notice/update/{nbid}")
	public String update(@ModelAttribute NoticeBoardDTO noticeBoardDTO) {
		noticeBoardService.update(noticeBoardDTO);
		return "redirect:/noticeboard/" + noticeBoardDTO.getNbid();
	}
	
	 //글 전체 목록
    @GetMapping("/notice/pagelist")
    public String getAllList(Model model) {
    	List<NoticeBoardDTO> noticeBoardDTOList = noticeBoardService.findAll();
    	model.addAttribute("noticeBoardList", noticeBoardDTOList);
    	return "notice/pagelist";
    }
	
    //상세보기
    @GetMapping("/notice/{nbid}")
    public String getDetail(@PathVariable Long nbid, Model model) {
    	NoticeBoardDTO noticeBoardDTO = noticeBoardService.findById(nbid);
    	model.addAttribute("noticeBoard", noticeBoardDTO);
    	return "notice/detail";
    }
	
	//삭제
	@GetMapping("/notice/delete/{nbid}")
	public String deleteHopeBoard(@PathVariable Long nbid) {
    	noticeBoardService.deleteById(nbid);
    	return "redirect:/notice/pagelist";
    }
			
}
