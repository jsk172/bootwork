package com.khit.board.entity;

import com.khit.board.dto.BoardDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@ToString(exclude = "member")
@Table(name = "t_board")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Board extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동순번
    private Integer id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false, length = 2000)
    private String content;

    //Board 엔티티와 연관관계 매핑
    //다대일 매핑(fetch는 조회할때 EAGER - 전체조회를 함, LAZY - 특정한 조회만 됨)
    //JoinColumn - 외래키 설정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Member member;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    @OrderBy("id desc")
    private List<Reply> replyList;

    public static Board toSaveEntity(BoardDTO boardDTO){
        Board board = Board.builder()
                .title(boardDTO.getTitle())
                .content(boardDTO.getContent())
                .member(boardDTO.getMember())
                .replyList(boardDTO.getReplyList())
                .build();
        return board;
    }
    public static Board toUpdateEntity(BoardDTO boardDTO){
        Board board = Board.builder()
                .id(boardDTO.getId())
                .title(boardDTO.getTitle())
                .content(boardDTO.getContent())
                .member(boardDTO.getMember())
                .replyList(boardDTO.getReplyList())
                .build();
        return board;
    }
}
