package com.khit.library.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.khit.library.entity.FreeBoard;
import com.khit.library.entity.HopeBoard;
import com.khit.library.entity.NoticeBoard;

public interface HopeBoardRepository extends JpaRepository<HopeBoard, Long>{
	
	
	@Modifying
	@Query(value="update HopeBoard b set b.hbhit=b.hbhit+1 where b.hbid=:hbid")
	public void updateHits(Long hbid);
	
	Page<HopeBoard> findAll(Pageable pageable); // 페이징 처리를 위한 메서드
}
