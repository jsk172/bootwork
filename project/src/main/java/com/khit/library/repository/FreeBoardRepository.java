package com.khit.library.repository;

import com.khit.library.entity.FreeBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreeBoardRepository extends JpaRepository<FreeBoard, Long> {
    Page<FreeBoard> findByFbtitleContainingOrFbcontentContaining(String title, String content, Pageable pageable);
    Page<FreeBoard> findByFbtitleContaining(String title, Pageable pageable);
    // 수정된 부분: member 엔터티의 name 속성을 기준으로 검색
    Page<FreeBoard> findByMember_NameContaining(String name, Pageable pageable);
}