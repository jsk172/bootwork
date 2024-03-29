package com.khit.board.dto;

import com.khit.board.entity.Board;
import com.khit.board.entity.Member;
import com.khit.board.entity.Reply;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
public class BoardDTO {
    private Integer id;
    private String title;
    private String content;
    private Member member;
    private List<Reply> replyList;

    private Timestamp createdDate;
    private Timestamp updatedDate;

    public static BoardDTO toSaveDTO(Board board){
        BoardDTO boardDTO = BoardDTO.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .member(board.getMember())
                .replyList(board.getReplyList())
                .createdDate(board.getCreatedDate())
                .updatedDate(board.getUpdatedDate())
                .build();
        return boardDTO;
    }

}
