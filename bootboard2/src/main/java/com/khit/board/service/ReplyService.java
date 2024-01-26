package com.khit.board.service;

import com.khit.board.entity.Board;
import com.khit.board.entity.Reply;
import com.khit.board.repository.BoardRepository;
import com.khit.board.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository;
    public void insertReply(Integer boardId, Reply reply) {
        //해당 게시글을 가져오기
        Board board = boardRepository.findById(boardId).get();
        reply.setBoard(board);
        //댓글 저장
        replyRepository.save(reply);
    }

    public void deleteById(Integer replyId) {
        replyRepository.deleteById(replyId);
    }
}
