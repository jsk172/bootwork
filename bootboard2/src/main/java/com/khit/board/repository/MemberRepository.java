package com.khit.board.repository;

import com.khit.board.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    //select * from member where member_id = ?;
    Optional<Member> findByMemberId(String string);

//    void update(Member member);
}
