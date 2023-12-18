package com.example.springexam03.service;

import com.example.springexam03.dto.BoardDTO;
import com.example.springexam03.dto.PageRequestDTO;
import com.example.springexam03.dto.PageResponseDTO;
import com.example.springexam03.vo.BoardAttachVO;

import java.util.List;

public interface BoardService {
    BoardDTO view(int bno);
    int modify(BoardDTO boardDTO);
    int remove(int bno);
    int totalCount();
    List<BoardDTO> getList();
    int register(BoardDTO dto);
    PageResponseDTO<BoardDTO> getPagingList(PageRequestDTO pageRequestDTO);

    List<BoardAttachVO> attachList(int bno);
}
