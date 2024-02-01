package com.khit.board.service;

import com.khit.board.dto.BoardDTO;
import com.khit.board.dto.MemberDTO;
import com.khit.board.entity.Board;
import com.khit.board.entity.Member;
import com.khit.board.exception.BootBoardException;
import com.khit.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    public List<BoardDTO> findAll() {
        List<Board> boardList = boardRepository.findAll(Sort.by(Sort.Direction.DESC, "id")); //내림차순
        List<BoardDTO> boardDTOList = new ArrayList<>();

        for(Board board : boardList){
            BoardDTO boardDTO = BoardDTO.toSaveDTO(board);
            boardDTOList.add(boardDTO);
        }

        return boardDTOList;
    }
    public BoardDTO findById(Integer id) {
        Optional<Board> findBoard = boardRepository.findById(id);
        if(findBoard.isPresent()){
            BoardDTO boardDTO = BoardDTO.toSaveDTO((findBoard.get()));
            return boardDTO;
        }else{
            throw new BootBoardException("페이지를 찾을 수 없습니다.");
        }
    }

    public void save(BoardDTO boardDTO) {
        Board board = Board.toSaveEntity(boardDTO);
        boardRepository.save(board);
    }

    public void deleteById(Integer id) {
        boardRepository.deleteById(id);
    }

    public void update(BoardDTO boardDTO) {
        Board board = Board.toUpdateEntity(boardDTO);
        boardRepository.save(board);
    }
}
