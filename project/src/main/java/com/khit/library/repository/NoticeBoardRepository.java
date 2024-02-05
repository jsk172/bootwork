package com.khit.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khit.library.entity.NoticeBoard;

public interface NoticeBoardRepository extends JpaRepository<NoticeBoard, Long>{

}