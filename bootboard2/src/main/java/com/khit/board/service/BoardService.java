package com.khit.board.service;

import com.khit.board.entity.Board;
import com.khit.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    public List<Board> findAll() {
        //내림차순
        return boardRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }
    public Board findById(Integer id) {
        return boardRepository.findById(id).get();
    }

    public void save(Board board) {
        boardRepository.save(board);
    }

    public void deleteById(Integer id) {
        boardRepository.deleteById(id);
    }
}
