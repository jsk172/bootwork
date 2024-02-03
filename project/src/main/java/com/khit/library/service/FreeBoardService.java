package com.khit.library.service;

import org.springframework.stereotype.Service;

import com.khit.library.dto.FreeBoardDTO;
import com.khit.library.entity.FreeBoard;
import com.khit.library.entity.FreeReply;
import com.khit.library.repository.FreeBoardRepository;
import com.khit.library.repository.FreeReplyRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FreeBoardService {
	private final FreeBoardRepository fboardRepository;
	private final FreeReplyRepository freeReplyRepository;

	public void insert(FreeBoard fboard) {
		fboardRepository.save(fboard);
	}

	public void insertReply(Long freeBoardFbid, FreeReply freeReply) {
		FreeBoard fboard = fboardRepository.findById(freeBoardFbid).get();
		freeReply.setFreeboard(fboard);
		// 댓글 저장
		freeReplyRepository.save(freeReply);
	}

}
