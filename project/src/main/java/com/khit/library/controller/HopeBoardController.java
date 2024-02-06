package com.khit.library.controller;

import com.khit.library.config.SecurityUser;
import com.khit.library.dto.HopeBoardDTO;
import com.khit.library.dto.MemberDTO;
import com.khit.library.entity.HopeBoard;
import com.khit.library.service.HopeBoardService;

import com.khit.library.service.MemberService;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class HopeBoardController {
    private final HopeBoardService hopeBoardService;
    private final MemberService memberService;

    // write page 글쓰기
    @GetMapping("/hopeboard/write")
    public String writeForm(@AuthenticationPrincipal SecurityUser principal, Model model) {
        if(principal == null){
            return "hopeboard/write";
        }else{
            MemberDTO memberDTO = memberService.findByMid(principal);
            model.addAttribute("member", memberDTO);
            return "hopeboard/write";
        }
    }
    
    // 글쓰기 처리
    @PostMapping("/hopeboard/write")
    public String write(@ModelAttribute HopeBoard hopeBoard,
    				    @AuthenticationPrincipal SecurityUser principal,
    				    MultipartFile hopeBoardFile
    				    /*BindingResult bindingResult*/) throws IOException, Exception {
    	hopeBoard.setMember(principal.getMember());
    	hopeBoard.setHbhit(0);
    	hopeBoardService.save(hopeBoard, hopeBoardFile);

        return "redirect:/hopeboard/pagelist";
    }

    // update page 글 수정
    @GetMapping("/hopeboard/update/{hbid}")
    public String updateForm(@PathVariable Long hbid, Model model, @AuthenticationPrincipal SecurityUser principal) {
    	HopeBoardDTO hopeBoardDTO = hopeBoardService.findById(hbid);
    	model.addAttribute("hopeBoard", hopeBoardDTO);
        if(principal == null){
            return "hopeboard/update";
        }else{
            MemberDTO memberDTO = memberService.findByMid(principal);
            model.addAttribute("member", memberDTO);
            return "hopeboard/update";
        }
    }
    
    // 글 수정 처리
    @PostMapping("/hopeboard/update/{hbid}")
    public String update(@ModelAttribute HopeBoardDTO hopeBoard,
    					
    		             MultipartFile hopeBoardFile,
    		             @AuthenticationPrincipal SecurityUser principal,
    		             Model model) throws IOException, Exception {
    	hopeBoard.setMember(principal.getMember());
    	HopeBoardDTO upHopeBoard =  hopeBoardService.update(hopeBoard, hopeBoardFile);
    	model.addAttribute("hopeBoard", upHopeBoard);
    	//System.out.println("Received mid: " + hopeBoard.getMember().getMid());
    	return "redirect:/hopeboard/" + upHopeBoard.getHbid();
    	//return "hopeboard/detail";
    }
    
    // 글 전체 목록
    @GetMapping("/hopeboard/pagelist")
    public String getAllList(Model model, @AuthenticationPrincipal SecurityUser principal) {
    	List<HopeBoardDTO> hopeBoardDTOList = hopeBoardService.findAll();
    	model.addAttribute("hopeBoardList", hopeBoardDTOList);
        if(principal == null){
            return "hopeboard/pagelist";
        }else{
            MemberDTO memberDTO = memberService.findByMid(principal);
            model.addAttribute("member", memberDTO);
            return "hopeboard/pagelist";
        }
    }
    
    // 글 하나 상세보기
    @GetMapping("/hopeboard/{hbid}")
    public String getDetail(@PathVariable Long hbid, Model model,
    						@AuthenticationPrincipal SecurityUser principal) {
    	hopeBoardService.updateHits(hbid);
    	HopeBoardDTO hopeBoardDTO = hopeBoardService.findById(hbid);
    	hopeBoardDTO.setMember(principal.getMember());
    	model.addAttribute("hopeBoard", hopeBoardDTO);
        if(principal == null){
            return "hopeboard/detail";
        }else{
            MemberDTO memberDTO = memberService.findByMid(principal);
            model.addAttribute("member", memberDTO);
            return "hopeboard/detail";
        }
    }
    
    // 글 삭제
    @GetMapping("/hopeboard/delete/{hbid}")
    public String deleteHopeBoard(@PathVariable Long hbid) {
    	hopeBoardService.deleteById(hbid);
    	return "redirect:/hopeboard/pagelist";
    }
}