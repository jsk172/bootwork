/*
package com.khit.library.controller;



import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import org.springframework.web.multipart.MultipartFile;

import com.khit.library.config.SecurityUser;
import com.khit.library.dto.EventDTO;
import com.khit.library.dto.MemberDTO;
import com.khit.library.entity.Event;
import com.khit.library.service.EventService;
import com.khit.library.service.MemberService;
import com.khit.library.service.NoticeBoardService;

import lombok.RequiredArgsConstructor;



@Controller
@RequiredArgsConstructor
@RequestMapping("/event")
public class EventController {
	
	private final EventService eventService;
    private final MemberService memberService;

	//쓰기 페이지
    @GetMapping("/write")
    public String writeForm(@AuthenticationPrincipal SecurityUser principal, Model model) {
        if(principal == null){
            return "event/write";
        }else{
            MemberDTO memberDTO = memberService.findByMid(principal);
            model.addAttribute("member", memberDTO);
            return "event/write";
        }
	}

	//쓰기 처리
	@PostMapping("/write")
	public String write(@ModelAttribute Event event,
						@AuthenticationPrincipal SecurityUser principal,
						MultipartFile eventFile) throws IOException, Exception {
		event.setMember(principal.getMember());
		event.setEvhit(0);
		eventService.save(event, eventFile);

		return "redirect:/event/pagelist";
	}
	
	//수정 페이지
	@GetMapping("/update/{evid}")
	public String updateForm(@PathVariable Long evid, Model model, @AuthenticationPrincipal SecurityUser principal) {
		EventDTO eventDTO = eventService.findById(evid);
		model.addAttribute("event", eventDTO);
        if(principal == null){
            return "event/update";
        }else{
            MemberDTO memberDTO = memberService.findByMid(principal);
            model.addAttribute("member", memberDTO);
            return "event/update";
        }
	}
	
	//수정 처리
	@PostMapping("/update/{evid}")
	public String update(@ModelAttribute EventDTO event,
						MultipartFile eventFile,
						@AuthenticationPrincipal SecurityUser principal,
    		            Model model) throws IOException, Exception {
		event.setMember(principal.getMember());
    	EventDTO upEvent =  eventService.update(event, eventFile);
    	model.addAttribute("event", upEvent);
		return "redirect:/event/" + event.getEvid();
	}
	
	//페이징, 글 목록
    @GetMapping("/pagelist")
    public String pagelist(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @AuthenticationPrincipal SecurityUser principal,
            Model model) {
        Pageable pageable = PageRequest.of(page, size);
        Page<EventDTO> eventPage = eventService.paging(pageable);
        List<EventDTO> eventDTOList = eventService.findAll();
        model.addAttribute("eventPage", eventPage);
        model.addAttribute("eventList", eventDTOList);
        if(principal == null){
            return "event/pagelist";
        }else{
            MemberDTO memberDTO = memberService.findByMid(principal);
            model.addAttribute("member", memberDTO);
            return "event/pagelist";
        }
    }
	
    //상세보기
    @GetMapping("/{evid}")
    public String getDetail(@PathVariable Long evid, Model model,
    						@AuthenticationPrincipal SecurityUser principal) {
    	eventService.updateHits(evid);
    	EventDTO eventDTO = eventService.findById(evid);
    	model.addAttribute("event", eventDTO);
        if(principal == null){
            return "event/detail";
        }else{
            MemberDTO memberDTO = memberService.findByMid(principal);
            model.addAttribute("member", memberDTO);
            return "event/detail";
        }
    }
	
	//삭제
	@GetMapping("/delete/{evid}")
	public String deleteEvent(@PathVariable Long evid) {
		eventService.deleteById(evid);
    	return "redirect:/event/pagelist";
    }
}
*/
