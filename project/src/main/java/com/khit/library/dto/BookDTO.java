package com.khit.library.dto;

import com.khit.library.entity.Book;
import com.khit.library.entity.RentalReturn;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
public class BookDTO {
    private Long bookId; // 도서번호
    private String bname; //도서이름
    private String bnumber; //도서고유번호
    private String author; //도서저자
    private boolean available;

    private List<RentalReturn> rentalReturnList;

    private Timestamp createdDate;
    private Timestamp updatedDate;

    public static BookDTO toSaveDTO(Book book){
        BookDTO bookDTO = BookDTO.builder()
                .bookId(book.getBookId())
                .bname(book.getBname())
                .bnumber(book.getBnumber())
                .author(book.getAuthor())
                .createdDate(book.getCreatedDate())
                .updatedDate(book.getUpdatedDate())
                .rentalReturnList(book.getRentalReturnList())
                .build();
        return bookDTO;
    }
}
