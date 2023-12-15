package com.example.sbboard.service;

import com.example.sbboard.model.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardService {
    Board register(Board board);
    Page<Board> getList(Pageable pageable);
    Board getBoard(Long bno);
    Board modify(Board board);
    void remove(Long bno);
    Long totalCount();
    List<Board> getByWriter(String writer);
    List<Board> getByContent(String keyword);
    //List<Board> getByTitleContent(String keyword);
}
