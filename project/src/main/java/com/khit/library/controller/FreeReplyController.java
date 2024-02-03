package com.khit.library.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.khit.library.config.SecurityUser;
import com.khit.library.entity.FreeReply;
import com.khit.library.service.FreeBoardService;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class FreeReplyController {
	private final FreeBoardService fboardService;
	
	@PostMapping("/freeReply/{fboardId}")
	@ResponseBody
	public String insertReply(@PathVariable Long fboardId,
							  @RequestBody FreeReply freeReply,
							  @AuthenticationPrincipal SecurityUser principal) {
		freeReply.setMember(principal.getMember());
		fboardService.insertReply(fboardId, freeReply);
		return "댓글 등록 성공";
	}
								
}
