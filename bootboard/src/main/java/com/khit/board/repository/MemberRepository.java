package com.khit.board.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khit.board.dto.MemberDTO;
import com.khit.board.entity.Member;

//JPA repository 상속받아야함
public interface MemberRepository extends JpaRepository<Member, Long>{

	//제공된 매서드 : save, findAll, findById, deleteById
	//쿼리 매서드(매서드 이름이 쿼리) : Optional(null체크 클래스)
	Optional<Member> findByMemberEmail(String memberEmail);


}
