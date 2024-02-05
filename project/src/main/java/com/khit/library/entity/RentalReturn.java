package com.khit.library.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.khit.library.dto.RentalReturnDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Data
@Table(name = "rentalreturn")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RentalReturn {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long rentalId; // 대출번호

	@Column(updatable = false)
	@CreationTimestamp
	private Timestamp rentalDate; //대출일

	@Column
	private Timestamp returnDate; //반납일

	@Column
	private Timestamp deadlineDate; //반납마감일

	public static RentalReturn toSaveEntity(RentalReturnDTO RentalReturnDTO){
		//반납일은 대출일 + 7일로 설정
		RentalReturn rentalReturn = RentalReturn.builder()
				.rentalDate(RentalReturnDTO.getRentalDate())
				.returnDate(RentalReturnDTO.getReturnDate())
				.deadlineDate(new Timestamp(RentalReturnDTO.getRentalDate().getTime() + 7*24*60*60*1000))
				.member(RentalReturnDTO.getMember())
				.book(RentalReturnDTO.getBook())
				.build();
		return rentalReturn;
	}
	public static RentalReturn toUpdateEntity(RentalReturnDTO RentalReturnDTO){
		RentalReturn rentalReturn = RentalReturn.builder()
				.rentalId(RentalReturnDTO.getRentalId())
				.rentalDate(RentalReturnDTO.getRentalDate())
				.returnDate(RentalReturnDTO.getReturnDate())
				.deadlineDate(RentalReturnDTO.getDeadlineDate())
				.member(RentalReturnDTO.getMember())
				.book(RentalReturnDTO.getBook())
				.build();
		return rentalReturn;
	}

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private Member member;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private Book book;
}
