package com.khit.library.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.khit.library.entity.Event;
import com.khit.library.entity.NoticeBoard;

public interface EventRepository extends JpaRepository<Event, Long>{
	
	//페이징 처리를 위한 메서드
	Page<Event> findAll(Pageable pageable);
	
	//조회수
	@Modifying
	@Query(value="update Event b set b.evhit=b.evhit+1 where b.evid=:evid")
	public void updateHits(Long evid);
}
