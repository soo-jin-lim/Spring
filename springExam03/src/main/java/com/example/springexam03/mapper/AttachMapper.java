package com.example.springexam03.mapper;

import com.example.springexam03.vo.BoardAttachVO;

import java.util.List;

public interface AttachMapper {
    void insertAttach(BoardAttachVO vo);
    void deleteAttach(String uuid);
    List<BoardAttachVO> findBno(int bno);
    void deleteAttachAll(int bno);
    public List<BoardAttachVO> getOldFiles();
}
