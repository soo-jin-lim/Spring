package com.example.sbjap001.repository;

import com.example.sbjap001.domain.Board;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class RepositoryTest {
    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void testPaging(){
        Pageable pageable= PageRequest.of(0,10,
                Sort.by("bno").descending());
        Page<Board> result=boardRepository.findByTitleContainingOrderByBnoDesc("1",pageable);

        log.info("total count: "+result.getTotalElements());
        log.info( "total pages:" +result.getTotalPages());
        log.info("page number: "+result.getNumber());
        log.info("page size: "+result.getSize());
        List<Board> boardList=result.getContent();
        boardList.forEach(board -> {
            log.info(board);
        });
    }

    @Test
    public void keyword1(){
        Pageable pageable= PageRequest.of(0,10,
                Sort.by("bno").descending());
        Page<Board> result=boardRepository.findBykeyword("1",pageable);

        log.info("total count: "+result.getTotalElements());
        log.info( "total pages:" +result.getTotalPages());
        log.info("page number: "+result.getNumber());
        log.info("page size: "+result.getSize());
        List<Board> boardList=result.getContent();
        boardList.forEach(board -> {
            log.info(board);
        });
    }
    @Test
    public void search1(){
        Pageable pageable=PageRequest.of(0,10, Sort.by("bno").descending());
        Page<Board> result=boardRepository.search1(pageable);
        log.info("total page:"+result.getTotalPages());
        log.info("total count:"+result.getTotalElements());
        log.info("page size:"+result.getSize());
        log.info("pageNumber:"+result.getNumber());
        log.info(result.hasPrevious()+":"+result.hasNext());
        result.getContent().forEach(board ->{
            log.info(board);
        } );

    }

    @Test
    public void searchAll(){
        String[] types={"t", "c","w"};
        String keyword="9";

        Pageable pageable=PageRequest.of(1, 10, Sort.by("bno").descending());
        Page<Board> result=boardRepository.searchAll(types, keyword,pageable);

        log.info("total page:"+result.getTotalPages());
        log.info("total count:"+result.getTotalElements());
        log.info("page size:"+result.getSize());
        log.info("pageNumber:"+result.getNumber());
        log.info(result.hasPrevious()+":"+result.hasNext());
        result.getContent().forEach(board ->{
            log.info(board);
        } );

    }

    @Test
    public void testInsert(){
        IntStream.rangeClosed(1, 100).forEach(i->{
            Board board=Board.builder()
                    .title("title...."+i)
                    .content("content...."+i)
                    .writer("user"+(i%10))
                    .build();
            Board result=boardRepository.save(board);
            log.info("bno:"+result.getBno());
        });
    }

    @Test
    public void testSelect(){
        Long bno=299L;
        Optional<Board> result=boardRepository.findById(bno);
        Board board=result.orElseThrow();
        log.info(board);
    }

    @Test
    public void testUpdate(){
        Long bno=1L;
        Optional<Board> result=boardRepository.findById(bno);
        Board board=result.orElseThrow();
        board.change("updte_title 1", "update Content 1", "user01");
        Board board1=boardRepository.save(board);
        log.info(board1);
    }

    @Test
    public void testDelete() {
        Long bno = 100L;
        boardRepository.deleteById(bno);
    }
}
