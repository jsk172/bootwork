
package com.khit.library.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.khit.library.dto.EventDTO;
import com.khit.library.entity.Event;
import com.khit.library.repository.EventRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EventService {
	
	private final EventRepository eventRepository;

	public List<Event> getAllEvents() {
		
		return null;
	}

	public Event getEventById(Long eventId) {
		
		return null;
	}

	public void save(Event event, MultipartFile eventFile) {
		// TODO Auto-generated method stub
		
	}

	public EventDTO findById(Long evid) {
		// TODO Auto-generated method stub
		return null;
	}

	public EventDTO update(EventDTO event, MultipartFile eventFile) {
		// TODO Auto-generated method stub
		return null;
	}

	public Page<EventDTO> paging(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<EventDTO> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateHits(Long evid) {
		// TODO Auto-generated method stub
		
	}

	public void deleteById(Long evid) {
		// TODO Auto-generated method stub
		
	}
	
}
