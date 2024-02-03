package com.khit.library.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.khit.library.dto.HopeBoardDTO;
import com.khit.library.entity.HopeBoard;
import com.khit.library.repository.HopeBoardRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class HopeBoardService {
	private final HopeBoardRepository hopeBoardRepository;

	public void save(HopeBoard hopeBoard) {
		hopeBoardRepository.save(hopeBoard);
	}

	public List<HopeBoardDTO> findAll() {
		List<HopeBoard> hopeBoardList = hopeBoardRepository.findAll(Sort.by(Sort.Direction.DESC, "hbid"));
		List<HopeBoardDTO> hopeBoardDTOList = new ArrayList<>();
		
		for(HopeBoard hopeBoard : hopeBoardList) {
			HopeBoardDTO hopeBoardDTO = HopeBoardDTO.toSaveDTO(hopeBoard);
			hopeBoardDTOList.add(hopeBoardDTO);
		}
		
		return hopeBoardDTOList;
	}

	public HopeBoardDTO findById(Long hbid) {
		Optional<HopeBoard> findHopeBoard = hopeBoardRepository.findById(hbid);
		HopeBoardDTO hopeBoardDTO = HopeBoardDTO.toSaveDTO(findHopeBoard.get());
		return hopeBoardDTO;
	}

	public void deleteById(Long hbid) {
		hopeBoardRepository.deleteById(hbid);
	}

	public void update(HopeBoardDTO hopeBoardDTO) {
		HopeBoard hopeBoard = HopeBoard.toUpdateEntity(hopeBoardDTO);
		hopeBoardRepository.save(hopeBoard);
	}
}
