package com.khit.library.entity;

import com.khit.library.dto.BookDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "book")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId; // 도서번호

    @Column(nullable = false, length = 40)
    private String bname; //도서이름

    @Column(unique = true, length = 20)
    private String bnumber; //도서고유번호

    @Column(nullable = false, length = 30)
    private String author; //도서저자

    public static Book toSaveEntity(BookDTO bookDTO){
        Book book = Book.builder()
                .bname(bookDTO.getBname())
                .bnumber(bookDTO.getBnumber())
                .author(bookDTO.getAuthor())
                .build();
        return book;
    }
    public static Book toUpdateEntity(BookDTO bookDTO){
        Book book = Book.builder()
                .bookId(bookDTO.getBookId())
                .bname(bookDTO.getBname())
                .bnumber(bookDTO.getBnumber())
                .author(bookDTO.getAuthor())
                .build();
        return book;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Member member;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<RentalReturn> rentalReturnList = new ArrayList<>();
}