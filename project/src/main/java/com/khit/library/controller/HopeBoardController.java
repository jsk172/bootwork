package com.khit.library.controller;

import com.khit.library.config.SecurityUser;
import com.khit.library.dto.HopeBoardDTO;
import com.khit.library.entity.HopeBoard;
import com.khit.library.service.HopeBoardService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class HopeBoardController {
    private final HopeBoardService hopeBoardService;

    // write page 글쓰기
    @GetMapping("/hopeboard/write")
    public String writeForm() {
        return "hopeboard/write";
    }
    
    // 글쓰기 처리
    @PostMapping("/hopeboard/write")
    public String write(@ModelAttribute HopeBoard hopeBoard,
    				    @AuthenticationPrincipal SecurityUser principal) {
    	hopeBoard.setMember(principal.getMember());
    	hopeBoard.setHbhit(0);
    	hopeBoardService.save(hopeBoard);
    	return "redirect:/hopeboard/pagelist";
    }

    // update page 글 수정
    @GetMapping("/hopeboard/update/{hbid}")
    public String updateForm(@PathVariable Long hbid, Model model) {
    	HopeBoardDTO hopeBoardDTO = hopeBoardService.findById(hbid);
    	model.addAttribute("hopeBoard", hopeBoardDTO);
    	return "hopeboard/update";
    }
    
    // 글 수정 처리
    @PostMapping("/hopeboard/update/{hbid}")
    public String update(@ModelAttribute HopeBoardDTO hopeBoardDTO) {
    	hopeBoardService.update(hopeBoardDTO);
    	return "redirect:/hopeboard/" + hopeBoardDTO.getHbid();
    }
    
    // 글 전체 목록
    @GetMapping("/hopeboard/pagelist")
    public String getAllList(Model model) {
    	List<HopeBoardDTO> hopeBoardDTOList = hopeBoardService.findAll();
    	model.addAttribute("hopeBoardList", hopeBoardDTOList);
    	return "hopeboard/pagelist";
    }
    
    // 글 하나 상세보기
    @GetMapping("/hopeboard/{hbid}")
    public String getDetail(@PathVariable Long hbid, Model model) {
    	HopeBoardDTO hopeBoardDTO = hopeBoardService.findById(hbid);
    	model.addAttribute("hopeBoard", hopeBoardDTO);
    	return "hopeboard/detail";
    }
    
    // 글 삭제
    @GetMapping("/hopeboard/delete/{hbid}")
    public String deleteHopeBoard(@PathVariable Long hbid) {
    	hopeBoardService.deleteById(hbid);
    	return "redirect:/hopeboard/pagelist";
    }
}
