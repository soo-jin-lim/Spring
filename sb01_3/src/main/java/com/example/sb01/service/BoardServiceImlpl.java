package com.example.sb01.service;

import com.example.sb01.dto.BoardDTO;
import com.example.sb01.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImlpl implements BoardService{
    private final BoardMapper boardMapper;
    @Override
    public List<BoardDTO> getList() {
        return boardMapper.getList();
    }

    @Override
    public int register(BoardDTO dto) {
        return boardMapper.insert(dto);
    }

    @Override
    public BoardDTO getBoard(int bno) {
        boardMapper.visitcountUpdate(bno);
        return boardMapper.getBoard(bno);
    }

    @Override
    public int modify(BoardDTO dto) {
        return boardMapper.update(dto);
    }

    @Override
    public int remove(int bno) {
        return boardMapper.delete(bno);
    }
}
