package com.khit.board.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Getter
@Setter
@ToString(exclude = "member")
@Table(name = "t_board")
@Entity
public class Board extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동순번
    private Integer id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false, length = 2000)
    private String content;
    @CreationTimestamp
    private Timestamp createdDate;

    //Board 엔티티와 연관관계 매핑
    //다대일 매핑(fetch는 조회할때 EAGER - 전체조회를 함, LAZY - 특정한 조회만 됨)
    //JoinColumn - 외래키 설정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
