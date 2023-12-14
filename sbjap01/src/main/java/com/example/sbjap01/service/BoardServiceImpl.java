package com.example.sbjap01.service;

import com.example.sbjap01.model.Board;
import com.example.sbjap01.service.BoardService;
import com.example.sbjap01.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardServiceImpl implements BoardService {
  @Autowired
  private BoardRepository boardRepository;
  @Override
  public Board register(Board board) {
    Board board1 = boardRepository.save(board);
    return board1;
  }

  @Override
  public List<Board> getList() {
    return boardRepository.findAll();
  }

  @Override
  public Board getBoard(long bno) {
    return boardRepository.findById(bno).get();
  }

  @Override
  public Board modify(Board board) {
    Board board1 = boardRepository.findById(board.getBno()).get();
    board1.setTitle(board.getTitle());
    board1.setContent(board.getContent());
    return boardRepository.save(board);
  }

  @Override
  public void remove(long bno) {
    boardRepository.deleteById(bno);
  }
}
