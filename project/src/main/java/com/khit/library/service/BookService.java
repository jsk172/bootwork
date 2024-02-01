package com.khit.library.service;

import com.khit.library.dto.BookDTO;
import com.khit.library.entity.Book;
import com.khit.library.exception.FinalException;
import com.khit.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;
    
    //책 등록
    public void save(BookDTO bookDTO) {
        Book book = Book.toSaveEntity(bookDTO);
        bookRepository.save(book);
    }
    
    //책 리스트
    public List<BookDTO> findAll() {
        List<Book> bookList = bookRepository.findAll();
        List<BookDTO> bookDTOList = new ArrayList<>();

        for(Book book : bookList){
            BookDTO bookDTO = BookDTO.toSaveDTO(book);
            bookDTOList.add(bookDTO);
        }
        return bookDTOList;
    }
    //상세보기
    public BookDTO findById(Long bookId) {
        Optional<Book> findBook = bookRepository.findById(bookId);
        if(findBook.isPresent()){
            BookDTO bookDTO = BookDTO.toSaveDTO(findBook.get());
            return bookDTO;
        }else{
            throw new FinalException("페이지를 찾을 수 없습니다.");
        }
    }
    //책 수정
    public void update(BookDTO bookDTO) {
        Book book = Book.toUpdateEntity(bookDTO);
        bookRepository.save(book);
    }

    public void deleteById(Long bookId) {
        bookRepository.deleteById(bookId);
    }
}
