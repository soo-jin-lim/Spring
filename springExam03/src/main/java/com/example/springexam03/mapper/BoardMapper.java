package com.example.springexam03.mapper;

import com.example.springexam03.dto.BoardDTO;
import com.example.springexam03.dto.PageRequestDTO;
import com.example.springexam03.vo.BoardVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
//MyBatis를 사용하여 데이터베이스와 상호 작용하기 위한 인터페이스
// XML 파일 (BoardMapper.xml)에서 실제 SQL 쿼리를 정의하고, 이를 자바 코드에서 호출하여 사용
//주로 게시글 정보를 조회, 수정, 삭제, 등록하는 SQL 쿼리가 정의
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
