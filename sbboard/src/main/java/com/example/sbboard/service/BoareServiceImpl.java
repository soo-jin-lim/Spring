package com.example.sbboard.service;

import com.example.sbboard.model.Board;
import com.example.sbboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoareServiceImpl implements BoardService{
    private final BoardRepository boardRepository;
    @Override
    public Board register(Board board) {
        return boardRepository.save(board);
    }

    public Page<Board> getList(Pageable pageable){
        return boardRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Board getBoard(Long bno) {
//        Board board=boardRepository.findById(bno).get();
//        return board;
        Board board=boardRepository.findById(bno).get();
        board.setVisitcount(board.getVisitcount()+1);
        boardRepository.save(board);
        //boardRepository.updateByVisitcount(bno);
        return boardRepository.findById(bno).get();
    }

    @Override
    public Board modify(Board board) {
        Optional<Board> board1=boardRepository.findById(board.getBno());
        Board board2=board1.get();
        board2.setTitle(board.getTitle());
        board2.setContent(board.getContent());
        board2.setWriter(board.getWriter());
        Board board3=boardRepository.save(board2);
        return board3;
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

    @Override
    public List<Board> getByWriter(String writer) {
        return boardRepository.findByWriter(writer);
    }

    @Override
    public List<Board> getByContent(String keyword) {
        return boardRepository.findByContentLike(keyword);
        //return boardRepository.findByContentContaining(keyword);
    }

//    @Override
//    public List<Board> getByTitleContent(String keyword) {
//        return boardRepository.findByTitleOrContentContains(keyword);
//    }


}
