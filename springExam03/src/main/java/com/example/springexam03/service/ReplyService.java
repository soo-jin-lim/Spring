package com.example.springexam03.service;

import com.example.springexam03.dto.ReplyDTO;

import java.util.List;

public interface ReplyService {
    int register(ReplyDTO dto);
    int remove(int rno);
    int modify(ReplyDTO dto);
    ReplyDTO read(int rno);
    List<ReplyDTO> getList(int bno);

}
