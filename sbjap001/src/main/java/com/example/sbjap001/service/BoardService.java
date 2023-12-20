package com.example.sbjap001.service;

import com.example.sbjap001.dto.BoardDTO;
import com.example.sbjap001.dto.BoardListReplyCountDTO;
import com.example.sbjap001.dto.PageRequestDTO;
import com.example.sbjap001.dto.PageResponseDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardService {
    Long register(BoardDTO boardDTO);
    List<BoardDTO> getList(Pageable pageable);
    BoardDTO getBoard(Long bno);
    Long modify(BoardDTO boardDTO);
    void remove(Long bno);
    PageResponseDTO<BoardDTO> listDsl(PageRequestDTO pageRequestDTO);

    PageResponseDTO<BoardListReplyCountDTO> listWidReplyCount(PageRequestDTO pageRequestDTO);
    //Long totalCount();
    //List<BoardDTO> getByWriter(String writer);
    //List<BoardDTO> getByContent(String keyword);
    //List<Board> getByTitleContent(String keyword);
}
