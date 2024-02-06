package com.khit.library.repository;

import com.khit.library.entity.FreeBoard;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreeBoardRepository extends JpaRepository<FreeBoard, Long> {

    Page<FreeBoard> findByFbtitleContainingOrFbcontentContaining(String title, String content, Pageable pageable);

	
}