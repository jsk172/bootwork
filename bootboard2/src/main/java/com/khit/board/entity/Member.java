package com.khit.board.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(exclude = "boardList") //순환참조 방지
@Table(name = "t_member")
public class Member extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동순번
    private Integer id; //회원번호
    @Column(name="member_id", unique = true)
    private String memberId; //아이디
    @Column(nullable = false)
    private String password; //비밀번호
    @Column(nullable = false, length = 30)
    private String name; //이름
//    @Column
    //private String role; //권한
    @Enumerated(EnumType.STRING)
    private Role role;

    //Board와 관계 매핑
    //주인 설정(다(Board)쪽이 주인)
    //cascade : 참조된 객체가 삭제되면 참조하는 객체도 삭제됨
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Board> boardList = new ArrayList<>();
}
