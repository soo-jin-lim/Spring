package com.example.springexam03.service;

import com.example.springexam03.dto.BoardDTO;
import com.example.springexam03.dto.PageRequestDTO;
import com.example.springexam03.dto.PageResponseDTO;
import com.example.springexam03.mapper.BoardMapper;
import com.example.springexam03.vo.BoardVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    private final BoardMapper boardMapper;
    private final ModelMapper modelMapper;

    @Override
    public BoardDTO view(int bno) {//게시글의 정보를 조회하는 메서드/ 조회된 정보를 BoardVO에서 BoardDTO로 변환하여 반환
        BoardVO boardVO=boardMapper.get(bno);//번호를 이용해서 게시글 정보를 데이터 베이스에서 가져옴
        BoardDTO boardDTO=modelMapper.map(boardVO, BoardDTO.class);
        if(boardDTO!=null){
            boardMapper.visitCountUpdate(bno);//게시글 조회수 업데이트
        }
        return boardDTO;
    }

    @Override
    public int modify(BoardDTO boardDTO) {//게시글 수정을 위한 메서드로 수정할 내용이 담긴 BoardDTO를 받아서 해당 게시글을 삭제합니다.
        BoardVO boardVO=modelMapper.map(boardDTO, BoardVO.class);
        int result=boardMapper.updateBoard(boardVO);//게시글 정보 업데이트
        log.info("service modify: result="+result);//로그에 수정 결과 출력
        return result;
    }

    @Override
    public int remove(int bno) {//게시글 삭제를 위한 메서드로 게시글 번호(bno)를 받아 해당 게시글을 삭제
        int result=boardMapper.deleteBoard(bno);//데이터 베이스에서 해당글을 삭제
        log.info("service remove: result="+result);//로그에 삭제 결과를 출력
        return result;
    }

    @Override
    public int totalCount() {//전체 게시글 수 반환
        return boardMapper.totalCount();//데이터 베이스의 전체 게시글 수를 조회.
    }

    @Override
    public List<BoardDTO> getList() {//전체 게시글 목록을 조회하는 메서드
        List<BoardDTO> boardDTOList=boardMapper.getBoardList().stream()//데이터 베이스에서 전체 게시글 목록을 가져옴
                .map(vo->modelMapper.map(vo,BoardDTO.class))
                .collect(Collectors.toList());//BoardVO를 BoardDTO로 변환한 후 리스트로 수집
        return boardDTOList;
    }

    @Override
    public int register(BoardDTO boardDTO) {//새로운 게시글을 등록함, BoardDTO를 받아서 정보를 db에 등록
        log.info("service register:"+boardDTO);
        BoardVO boardVO=modelMapper.map(boardDTO,BoardVO.class);//BoardDTO를 BoardVO로 매핑합니다.
        int result=boardMapper.insertBoard(boardVO);//데이터 베이스를 새로운 게시글에 등록
        return result;
    }

    @Override
    public PageResponseDTO<BoardDTO> getPagingList(PageRequestDTO pageRequestDTO) {//페이질 처리된 게시글 목록을 조회
        List<BoardVO> voList=boardMapper.pagingList(pageRequestDTO);//게시글 목록을 가져옴
        List<BoardDTO> dtoList=voList.stream()
                .map(vo->modelMapper.map(vo, BoardDTO.class))
                .collect(Collectors.toList());//ModelMapper를 사용하여 BoardVO를 BoardDTO로 변환한 후 리스트로 수집합니다.

        int total=boardMapper.pagingCount(pageRequestDTO);//페이징 처리를 위해 전체 레코드 수를 조회함

        PageResponseDTO<BoardDTO> pageResponseDTO=PageResponseDTO.<BoardDTO>withAll()
                .dtoList(dtoList) // List<boardDTO>
                .total(total)   // 전체 레코드 수
                .pageRequestDTO(pageRequestDTO) //paging 정보
                .build();
        return pageResponseDTO;
    }
}
