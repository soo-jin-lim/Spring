package com.example.springexam03.service;

import com.example.springexam03.dto.BoardDTO;
import com.example.springexam03.dto.PageRequestDTO;
import com.example.springexam03.dto.PageResponseDTO;
import com.example.springexam03.mapper.AttachMapper;
import com.example.springexam03.mapper.BoardMapper;
import com.example.springexam03.vo.BoardAttachVO;
import com.example.springexam03.vo.BoardVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    private final BoardMapper boardMapper;
    private final ModelMapper modelMapper;
    private final AttachMapper attachMapper;

    @Override
    public BoardDTO view(int bno) {
        BoardVO boardVO=boardMapper.get(bno);
        BoardDTO boardDTO=modelMapper.map(boardVO, BoardDTO.class);
        boardDTO.setAttachVOList(attachMapper.findBno(bno));
        if(boardDTO!=null){
            boardMapper.visitCountUpdate(bno);
        }
        return boardDTO;
    }

    @Transactional
    @Override
    public int modify(BoardDTO boardDTO) {
        if(boardDTO.getAttachVOList()==null){
            List<BoardAttachVO> attachVOList=attachMapper.findBno(boardDTO.getBno());
            boardDTO.setAttachVOList(attachVOList);
        }
        BoardVO boardVO=modelMapper.map(boardDTO, BoardVO.class);
        attachMapper.deleteAttachAll(boardVO.getBno());
        int result=boardMapper.updateBoard(boardVO);
        if(result==1 && boardDTO.getAttachVOList().size()>0){
            boardDTO.getAttachVOList().forEach(attach->{
                attach.setBno(boardDTO.getBno());
                attachMapper.insertAttach(attach);
            });
        }
        log.info("service modify: result="+result);
        return result;
    }
    @Transactional
    @Override
    public int remove(int bno) {
        attachMapper.deleteAttachAll(bno);
        int result=boardMapper.deleteBoard(bno);
        log.info("service remove: result="+result);
        return result;
    }

    @Override
    public int totalCount() {
        return boardMapper.totalCount();
    }

    @Override
    public List<BoardDTO> getList() {
        List<BoardDTO> boardDTOList=boardMapper.getBoardList().stream()
                .map(vo->modelMapper.map(vo,BoardDTO.class))
                .collect(Collectors.toList());
        return boardDTOList;
    }
    @Transactional
    @Override
    public int register(BoardDTO boardDTO) {
        log.info("service register:"+boardDTO);

        BoardVO boardVO=modelMapper.map(boardDTO,BoardVO.class);
        int result=boardMapper.insertBoard(boardVO);
        if(boardDTO.getAttachVOList() !=null || boardDTO.getAttachVOList().size()>0){
            boardDTO.getAttachVOList().forEach(attach ->{
                attachMapper.insertAttach(attach);
            });
        }
        return result;
    }

    @Override
    public PageResponseDTO<BoardDTO> getPagingList(PageRequestDTO pageRequestDTO) {
        List<BoardVO> voList=boardMapper.pagingList(pageRequestDTO);
        List<BoardDTO> dtoList=voList.stream()
                .map(vo->modelMapper.map(vo, BoardDTO.class))
                .collect(Collectors.toList());

        int total=boardMapper.pagingCount(pageRequestDTO);

        PageResponseDTO<BoardDTO> pageResponseDTO=PageResponseDTO.<BoardDTO>withAll()
                .dtoList(dtoList) // List<boardDTO>
                .total(total)   // 전체 레코드 수
                .pageRequestDTO(pageRequestDTO) //paging 정보
                .build();
        return pageResponseDTO;
    }

    @Override
    public List<BoardAttachVO> attachList(int bno) {
        return attachMapper.findBno(bno);
    }
}
