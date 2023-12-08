package com.example.springexam03.service;

import com.example.springexam03.dto.BoardDTO;
import com.example.springexam03.dto.PageRequestDTO;
import com.example.springexam03.dto.PageResponseDTO;

import java.util.List;
//BoardServiceImpl 클래스에서 이 인터페이스를 구현/ 게시글 조회, 수정, 삭제, 목록 조회, 등록, 페이징된 목록 조회 등의 메서드가 정의
public interface BoardService {//게시글과 관련된 기능을 정의, 게시글의 조회,수정,삭제,목록,가져오기,
    BoardDTO view(int bno);//기능: 게시글 조회/ 파라미터: 게시글번호 (bno)/ 반환값:조회된 게시글 정보(BoardDTO)
    int modify(BoardDTO boardDTO);//기능: 게시글 수정/ 파라미터: 수정할 게시글 번호/ 반환값: 수정된 레코드의 수
    int remove(int bno);//기능: 게시글 삭제/ 파라미터:삭제할 게시글 번호/ 반환값: 삭제된 레코드의 수
    int totalCount();//기능: 전체 게시글 조회/ 반환값: 전체 게시글 수
    List<BoardDTO> getList();//기능: 전체 게시글 목록 조회/ 반환값 : 전체 게시글 목록(List<BoardDTO>)
    int register(BoardDTO dto);// 기능:새로운 게시글 등록/ 파라미터: 등록할 게시글 정보/ 반환값:등록된 레코드의 수
    PageResponseDTO<BoardDTO> getPagingList(PageRequestDTO pageRequestDTO);//기능:페이징된 게시글의 목록 조회/ 파라미터: 페이징 정보(PageRequestDTO)/반횐값:페이징된 게시글 목록 및 페이징 정보 (PageResponseDTO<>BoardDTO)
}
