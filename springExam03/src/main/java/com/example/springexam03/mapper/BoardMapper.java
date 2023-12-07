package com.example.springexam03.mapper;

import com.example.springexam03.dto.BoardDTO;
import com.example.springexam03.dto.PageRequestDTO;
import com.example.springexam03.vo.BoardVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {
    void updateReplycount(@Param("bno") int bno, @Param("amount") int amount);
    List<BoardVO> pagingList(PageRequestDTO pageRequestDTO);
    int pagingCount(PageRequestDTO pageRequestDTO);
    String getTime();
    BoardVO get(int bno);
    void visitCountUpdate(int bno);
    int updateBoard(BoardVO boardVO);
    int deleteBoard(int bno);
    int totalCount();
    List<BoardVO> getBoardList();
    int insertBoard(BoardVO boardVO);
}
