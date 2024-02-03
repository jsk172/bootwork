package com.khit.library.service;

import com.khit.library.dto.RentalReturnDTO;
import com.khit.library.entity.RentalReturn;
import com.khit.library.repository.RentalReturnRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalReturnService {
    private final RentalReturnRepository rentalReturnRepository;

    //대출리스트
    public List<RentalReturnDTO> findAll() {
        List<RentalReturn> rentalReturnList = rentalReturnRepository.findAll();
        List<RentalReturnDTO> rentalReturnDTOList = new ArrayList<>();

        for(RentalReturn rentalReturn : rentalReturnList){
            RentalReturnDTO rentalReturnDTO = RentalReturnDTO.toSaveDTO(rentalReturn);
            rentalReturnDTOList.add(rentalReturnDTO);
        }
        return rentalReturnDTOList;
    }

    //책 대출
    public void save(RentalReturnDTO rentalReturnDTO) {
        RentalReturn rentalReturn = RentalReturn.toSaveEntity(rentalReturnDTO);
        rentalReturnRepository.save(rentalReturn);
    }
}
