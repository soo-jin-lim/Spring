package com.example.sbjap001.repository;

import com.example.sbjap001.domain.Board;
import com.example.sbjap001.domain.Reply;
import com.example.sbjap001.dto.BoardListReplyCountDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@SpringBootTest
@Log4j2
public class ReplyRepositoryTest {
    @Autowired
    private ReplyRepository replyRepository;
    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void testInsert(){
        Long bno=299L;
        Board board=Board.builder().bno(bno).build();
        Reply reply= Reply.builder()
                .board(board)
                .replytext("댓글 내용")
                .replyer("replyer1")
                .build();
        replyRepository.save(reply);
    }

    @Test
    public void testRepliesSelect(){
        Long bno=299L;
        Pageable pageable= PageRequest.of(0, 10,
                Sort.by("rno").descending());
        Page<Reply> result=replyRepository.listOfBoard(bno, pageable);
        result.forEach(reply -> {
            log.info(reply);
        });
    }
    @Test
    public void testSelectReplyCount(){
        String[] types={"t","w","c"};
        String keyword="1";
        Pageable pageable=PageRequest.of(5, 10, Sort.by("bno")
                .descending());
        Page<BoardListReplyCountDTO> result=
                boardRepository.searchWithReplyCount(types,keyword,pageable);
        log.info(result.getTotalElements());
        log.info(result.getTotalPages());
        log.info(result.getSize());
        log.info(result.getNumber());
        log.info(result.hasPrevious()+":"+result.hasNext());
        result.getContent().forEach(board->{
            log.info(board);
        });
    }
}
