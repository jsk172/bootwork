package com.khit.library.service;

import org.springframework.stereotype.Service;

import com.khit.library.repository.FreeBoardRepository;
import com.khit.library.repository.FreeReplyRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FreeReplyService {
	
	private final FreeBoardRepository fboardRepository;
	private final FreeReplyRepository freplyRepository;
	
	
}
