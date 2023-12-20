package com.example.sbjap001.service;

import com.example.sbjap001.dto.PageRequestDTO;
import com.example.sbjap001.dto.PageResponseDTO;
import com.example.sbjap001.dto.ReplyDTO;

public interface ReplyService {
    Long register(ReplyDTO dto);
    ReplyDTO read(Long rno);
    void modify(ReplyDTO replyDTO);
    void remove(Long rno);
    PageResponseDTO<ReplyDTO> getListOfBoard(Long bno, PageRequestDTO pageRequestDTO);
}








