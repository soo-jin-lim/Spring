package com.example.springexam03.service;

import com.example.springexam03.dto.ReplyDTO;

import java.util.List;

public interface ReplyService {
    int register(ReplyDTO dto);//댓글을 등록하는 메서드, 댓글의 갯수 반환
    int remove(int rno);//댓글을 삭제하는 메서드,(rno)를 메개변수로 받아 해당 댓글 삭제
    int modify(ReplyDTO dto);//댓글을 수정하는 메서드
    ReplyDTO read(int rno);//댓글을 조회하는 메서드
    List<ReplyDTO> getList(int bno);//특정 게시글에 대한 댓글 목록 조회
}
