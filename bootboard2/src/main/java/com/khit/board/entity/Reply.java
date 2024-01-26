package com.khit.board.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "t_reply")
public class Reply extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200, nullable = false)
    private String content;

    //회원 1명이 여러 개의 댓글을 쓸 수 있음
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Member member;

    //1개의 게시글에 여러개의 댓글이 달림
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Board board;
}
