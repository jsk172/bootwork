package com.khit.board.controller;

import com.khit.board.config.SecurityUser;
import com.khit.board.entity.Reply;
import com.khit.board.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyService replyService;

    @PostMapping("/reply/{boardId}")
    public @ResponseBody String insertReply(@PathVariable Integer boardId, @RequestBody Reply reply, @AuthenticationPrincipal SecurityUser principal){
        reply.setMember(principal.getMember());
        replyService.insertReply(boardId, reply);
        return "댓글등록완료";
    }
    @DeleteMapping("/reply/{replyId}")
    public @ResponseBody String deleteReply(@PathVariable Integer replyId){
        replyService.deleteById(replyId);
        return "댓글삭제완료";
    }
}
