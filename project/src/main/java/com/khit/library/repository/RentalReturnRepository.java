package com.khit.library.repository;

import com.khit.library.entity.RentalReturn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RentalReturnRepository extends JpaRepository<RentalReturn, Long> {
    //Optional<RentalReturn> findByRentalId(Long rentalId);
    RentalReturn findByRentalId(Long rentalId);

    List<RentalReturn> findByMemberMid(String mid);

    @Query("select count(*) from RentalReturn where member.memberId = :memberId and returnDate is null")
    public int rentalCount(Long memberId);

    @Query("select r1.member.memberId from RentalReturn as r1 " +
            "where(select count(*) from RentalReturn as r2 " +
            "where r2.member.memberId = r1.member.memberId " +
            "and r2.returnDate is null) >= 5 " +
            "or (r1.deadlineDate < current_date() and r1.returnDate is null) " +
            "group by r1.member.memberId")
    public List<Integer> rentalAble();
}

