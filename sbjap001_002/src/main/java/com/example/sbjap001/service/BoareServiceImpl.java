package com.example.sbjap001.service;

import com.example.sbjap001.dto.BoardDTO;
import com.example.sbjap001.domain.Board;
import com.example.sbjap001.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoareServiceImpl implements BoardService{
    private final BoardRepository boardRepository;
    private final ModelMapper modelMapper;

    @Override
    public Long register(BoardDTO boardDTO) {
        Board board=modelMapper.map(boardDTO, Board.class);
        Long bno=boardRepository.save(board).getBno();
         return bno;
    }

    public List<BoardDTO> getList(Pageable pageable){
        Page<Board> result=boardRepository.findAll(pageable);
        List<BoardDTO> dtoList=result.getContent().stream()
                .map(board -> modelMapper.map(board,BoardDTO.class))
                .collect(Collectors.toList());
        return dtoList;
    }

    @Transactional
    @Override
    public BoardDTO getBoard(Long bno) {
        Optional<Board> result=boardRepository.findById(bno);
        Board board=result.get();
        board.updateVisitcount();
        BoardDTO boardDTO=modelMapper.map(board,BoardDTO.class);

        return boardDTO;
    }

    @Override
    public Long modify(BoardDTO boardDTO) {
        Optional<Board> result=boardRepository.findById(boardDTO.getBno());
        Board board=result.orElseThrow();
        board.change(boardDTO.getTitle(), boardDTO.getContent(), boardDTO.getWriter());
        Long bno=boardRepository.save(board).getBno();
        return bno;
    }

    @Override
    public void remove(Long bno) {

        boardRepository.deleteById(bno);
    }

    @Override
    public Long totalCount() {
        Long totalCount=boardRepository.count();
        return totalCount;
    }

//    @Override
//    public List<BoardDTO> getByWriter(String writer) {
//
//        return null; //boardRepository.findByWriter(writer);
//    }

//    @Override
//    public List<BoardDTO> getByContent(String keyword) {
//        return null; //boardRepository.findByContentLike(keyword);
//        //return boardRepository.findByContentContaining(keyword);
//    }

//    @Override
//    public List<Board> getByTitleContent(String keyword) {
//        return boardRepository.findByTitleOrContentContains(keyword);
//    }


}
