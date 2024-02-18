package com.khit.library.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.khit.library.dto.EventDTO;
import com.khit.library.entity.Event;
import com.khit.library.repository.EventRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EventService {

    private final EventRepository eventRepository;

    public void save(EventDTO eventDTO, MultipartFile eventFile) throws Exception, IOException {
        if(!eventFile.isEmpty()) {
            UUID uuid = UUID.randomUUID();
            String eventFilename = uuid + "_" + eventFile.getOriginalFilename();
            
            //String eventFilepath ="C:/springfiles/" + eventFilename;
            String eventFilepath ="/Users/Healer/springfiles/" + eventFilename; // 희린 전용

            File savedEventFile = new File(eventFilepath); // 실제 저장된 파일
            eventFile.transferTo(savedEventFile);

            eventDTO.setEventFilename(eventFilename);
            eventDTO.setEventFilepath(eventFilepath); // 파일 경로 설정
        }
        Event event = Event.toSaveEntity(eventDTO);
        eventRepository.save(event);
    }

    public EventDTO findById(Long evid) {
        Optional<Event> findEvent = eventRepository.findById(evid);
        return EventDTO.toSaveDTO(findEvent.get());
    }

    public EventDTO update(EventDTO eventDTO, MultipartFile eventFile) throws Exception, IOException {
        if(!eventFile.isEmpty()) {
            UUID uuid = UUID.randomUUID();
            String eventFilename = uuid + "_" + eventFile.getOriginalFilename();
            //String eventFilepath ="C:/springfiles/" + eventFilename;
            String eventFilepath ="/Users/Healer/springfiles/" + eventFilename; // 희린 전

            File savedEventFile = new File(eventFilepath); // 실제 저장된 파일
            eventFile.transferTo(savedEventFile);

            eventDTO.setEventFilename(eventFilename);
            eventDTO.setEventFilepath(eventFilepath);
        } else {
            eventDTO.setEventFilename(findById(eventDTO.getEvid()).getEventFilename());
            eventDTO.setEventFilepath(findById(eventDTO.getEvid()).getEventFilepath());
        }
        Event event = Event.toUpdateEntity(eventDTO);
        eventRepository.save(event);
        return findById(eventDTO.getEvid());
    }

    public void deleteById(Long evid) {
        eventRepository.deleteById(evid);
    }
    
    public List<EventDTO> findAll() {
        List<Event> eventList = eventRepository.findAll(Sort.by(Sort.Direction.DESC, "evid"));
        List<EventDTO> eventDTOList = new ArrayList<>();
        
        for(Event event : eventList) {
            EventDTO eventDTO = EventDTO.toSaveDTO(event);
            eventDTOList.add(eventDTO);
        }
        
        return eventDTOList;
    }

    // 페이징
    public Page<EventDTO> paging(Pageable pageable) {
        Page<Event> eventPage = eventRepository.findAll(pageable);
        return eventPage.map(event -> EventDTO.toSaveDTO(event));
    }

    // 조회수
    @Transactional
    public void updateHits(Long evid) {
        eventRepository.updateHits(evid);
    }
}