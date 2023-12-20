package com.example.sbjap001.service;

import com.example.sbjap001.domain.Board;
import com.example.sbjap001.domain.Reply;
import com.example.sbjap001.dto.PageRequestDTO;
import com.example.sbjap001.dto.PageResponseDTO;
import com.example.sbjap001.dto.ReplyDTO;
import com.example.sbjap001.repository.BoardRepository;
import com.example.sbjap001.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService{
    private final ReplyRepository replyRepository;
    private final ModelMapper modelMapper;
    private final BoardRepository boardRepository;
    @Override
    public Long register(ReplyDTO dto) {
        Reply reply=modelMapper.map(dto, Reply.class);
//        Board board=boardRepository.findById(dto.getBno()).get();
//        board.updateReplycout();
//        boardRepository.save(board);
        Board board=Board.builder().bno(dto.getBno()).build();
        reply.setBoard(board);
        Long rno=replyRepository.save(reply).getRno();
        return rno;
    }

    @Override
    public ReplyDTO read(Long rno) {
        Optional<Reply> result=replyRepository.findById(rno);
        Reply reply=result.get();
        ReplyDTO dto=modelMapper.map(reply, ReplyDTO.class);
        return dto;
    }

    @Override
    public void modify(ReplyDTO replyDTO) {
        Reply reply=replyRepository.findById(replyDTO.getRno()).get();
        reply.changeText(replyDTO.getReplytext());
        replyRepository.save(reply);
    }

    @Override
    public void remove(Long rno) {
        replyRepository.deleteById(rno);
    }

    @Override
    public PageResponseDTO<ReplyDTO> getListOfBoard(Long bno, PageRequestDTO pageRequestDTO) {
        Pageable pageable= PageRequest.of(
                pageRequestDTO.getPage() <=0? 0: pageRequestDTO.getPage() -1,
                pageRequestDTO.getSize(),
                Sort.by("rno").descending());

        Page<Reply> result=replyRepository.listOfBoard(bno, pageable);
        List<ReplyDTO> dtoList=result.getContent().stream()
                .map(reply -> modelMapper.map(reply, ReplyDTO.class))
                .collect(Collectors.toList());

        return PageResponseDTO.<ReplyDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    }
}
