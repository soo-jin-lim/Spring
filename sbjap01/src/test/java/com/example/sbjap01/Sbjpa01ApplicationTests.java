package com.example.sbjap01;

import com.example.sbjap01.model.Board;
import com.example.sbjap01.service.BoardService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Log4j2
@SpringBootTest
class Sbjap01ApplicationTests {
  @Autowired
  BoardService service;
  @Test
  void insertTest() throws Exception{
    Board board = new Board();
    board.setTitle("title");
    board.setContent("content");
    board.setWriter("user");
    log.info(service.register(board));

  }
  @Test
  void list()throws Exception{
    List<Board> list=service.getList();
    list.forEach(board -> {
      log.info(board);
    });
  }

}
