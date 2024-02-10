package com.khit.library.repository;

import com.khit.library.entity.RentalReturn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RentalReturnRepository extends JpaRepository<RentalReturn, Long> {
    //Optional<RentalReturn> findByRentalId(Long rentalId);
    RentalReturn findByRentalId(Long rentalId);

    List<RentalReturn> findByMemberMid(String mid);

}
