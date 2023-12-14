package com.example.sbjap01.service;

import com.example.sbjap01.model.Board;

import java.util.List;

public interface BoardService {
  Board register(Board board);
  List<Board> getList();
  Board getBoard(long bno);
  Board modify(Board board);
  void remove(long bno);
}
