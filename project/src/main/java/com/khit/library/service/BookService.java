package com.khit.library.service;

import com.khit.library.dto.BookDTO;
import com.khit.library.dto.MemberDTO;
import com.khit.library.dto.RentalReturnDTO;
import com.khit.library.entity.Book;
import com.khit.library.entity.RentalReturn;
import com.khit.library.exception.FinalException;
import com.khit.library.repository.BookRepository;
import com.khit.library.repository.RentalReturnRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;
    private final RentalReturnRepository rentalReturnRepository;
    
    //책 등록
    public void save(BookDTO bookDTO, MultipartFile bookFile) throws Exception {
        if(!bookFile.isEmpty()){
            String bfilepath = "C:\\bootworks\\project\\src\\main\\resources\\static\\upload\\";
            UUID uuid = UUID.randomUUID();
            String bfilename = uuid + "_" + bookFile.getOriginalFilename(); //원본파일

            File savedFile = new File(bfilepath, bfilename);
            bookFile.transferTo(savedFile);

            bookDTO.setBfilename(bfilename);
            bookDTO.setBfilepath("/upload/" + bfilename);
        }
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
    //책 삭제
    public void deleteById(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    public void rentBook(Long bookId, MemberDTO memberDTO) {
    }
}
