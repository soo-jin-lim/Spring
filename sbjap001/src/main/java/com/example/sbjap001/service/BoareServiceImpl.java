package com.example.sbjap001.service;

import com.example.sbjap001.dto.BoardDTO;
import com.example.sbjap001.domain.Board;
import com.example.sbjap001.dto.BoardListReplyCountDTO;
import com.example.sbjap001.dto.PageRequestDTO;
import com.example.sbjap001.dto.PageResponseDTO;
import com.example.sbjap001.repository.BoardRepository;
import com.example.sbjap001.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    private final ReplyRepository replyRepository;

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
    public PageResponseDTO<BoardDTO> listDsl(PageRequestDTO pageRequestDTO) {
        String[] types=pageRequestDTO.getTypes();
        String keyword=pageRequestDTO.getKeyword();
        Pageable pageable=pageRequestDTO.getPageable("bno");
        Page<Board> result=boardRepository.searchAll(types, keyword, pageable);
        //int count=replyRepository.listOfBoard(bno).getSize();

        List<BoardDTO> dtoList=result.getContent().stream()
                .map(board -> modelMapper.map(board, BoardDTO.class))
                .collect(Collectors.toList());

        return PageResponseDTO.<BoardDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    }

    @Override
    public PageResponseDTO<BoardListReplyCountDTO> listWidReplyCount(PageRequestDTO pageRequestDTO) {
        String[] types=pageRequestDTO.getTypes();
        String keyword=pageRequestDTO.getKeyword();
        Pageable pageable=pageRequestDTO.getPageable("bno");
        //Page<Board> result=boardRepository.searchAll(types, keyword, pageable);

        Page<BoardListReplyCountDTO> result=
                boardRepository.searchWithReplyCount(types,keyword,pageable);

        return PageResponseDTO.<BoardListReplyCountDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(result.getContent())
                .total((int)result.getTotalElements())
                .build();
    }


//    @Override
//    public Long totalCount() {
//        Long totalCount=boardRepository.count();
//        return totalCount;
//    }

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
