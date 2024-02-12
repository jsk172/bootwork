package com.khit.library.repository;

import com.khit.library.entity.FreeBoard;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FreeBoardRepository extends JpaRepository<FreeBoard, Long> {
    Page<FreeBoard> findByFbtitleContainingOrFbcontentContaining(String title, String content, Pageable pageable);
    Page<FreeBoard> findByFbtitleContaining(String title, Pageable pageable);
    // 수정된 부분: member 엔터티의 name 속성을 기준으로 검색
    Page<FreeBoard> findByMember_NameContaining(String name, Pageable pageable);
    @Modifying
	@Query(value="update FreeBoard b set b.fbhit=b.fbhit+1 where b.fbid=:fbid")
	public void updateHits(Long fbid);
    
	//페이징 처리를 위한 메서드
	Page<FreeBoard> findAll(Pageable pageable);
}