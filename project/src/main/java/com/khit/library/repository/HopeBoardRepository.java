package com.khit.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.khit.library.entity.HopeBoard;

public interface HopeBoardRepository extends JpaRepository<HopeBoard, Long>{
	@Modifying
	@Query(value="update HopeBoard b set b.hbhit=b.hbhit+1 where b.hbid=:hbid")
	public void updateHits(Long hbid);
}
