package com.khit.library.dto;

import com.khit.library.entity.Book;
import com.khit.library.entity.RentalReturn;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class BookDTO {
    private Long bookId; // 도서번호
    private String bname; //도서이름
    private String bnumber; //도서고유번호
    private String author; //도서저자
    private RentalReturn rentalReturn;

    private Timestamp createdDate;
    private Timestamp updatedDate;

    public static BookDTO toSaveDTO(Book book){
        BookDTO bookDTO = BookDTO.builder()
                .bookId(book.getBookId())
                .bname(book.getBname())
                .bnumber(book.getBnumber())
                .author(book.getAuthor())
//                .rentalReturn(book.getRentalReturnList())
                .createdDate(book.getCreatedDate())
                .updatedDate(book.getUpdatedDate())
                .build();
        return bookDTO;
    }
}
