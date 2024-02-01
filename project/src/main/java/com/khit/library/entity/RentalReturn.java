package com.khit.library.entity;

import java.sql.Timestamp;

import com.khit.library.dto.RentalReturnDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Data
@Table(name = "rentalreturn")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RentalReturn {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long RentalId; // 대출번호

	@Column(updatable = false)
	@CreationTimestamp
	private Timestamp rentalDate; //대출일

	@Column
	@CreationTimestamp
	private Timestamp returnDate; //반납일

	@Column
	@CreationTimestamp
	private Timestamp deadlineDate; //반납마감일

	public static RentalReturn toSaveEntity(RentalReturnDTO RentalReturnDTO){
		//반납일은 대출일 + 7일로 설정
		Timestamp deadlineDate = new Timestamp(RentalReturnDTO.getRentalDate().getTime() + 7*24*60*60*1000);
		RentalReturn rentalReturn = RentalReturn.builder()
				.rentalDate(RentalReturnDTO.getRentalDate())
				.returnDate(RentalReturnDTO.getReturnDate())
				.deadlineDate(RentalReturnDTO.getDeadlineDate())
				.member(RentalReturnDTO.getMember())
				.book(RentalReturnDTO.getBook())
				.build();
		return rentalReturn;
	}
	public static RentalReturn toUpdateEntity(RentalReturnDTO RentalReturnDTO){
		RentalReturn rentalReturn = RentalReturn.builder()
				.RentalId(RentalReturnDTO.getRentalId())
				.rentalDate(RentalReturnDTO.getRentalDate())
				.returnDate(RentalReturnDTO.getReturnDate())
				.deadlineDate(RentalReturnDTO.getDeadlineDate())
				.member(RentalReturnDTO.getMember())
				.book(RentalReturnDTO.getBook())
				.build();
		return rentalReturn;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private Member member;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private Book book;
}
