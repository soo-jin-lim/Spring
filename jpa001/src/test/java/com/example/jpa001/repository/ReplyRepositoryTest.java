package com.example.jpa001.repository;

import com.example.jpa001.domain.Board;
import com.example.jpa001.domain.Reply;
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
@Test
    public void testInsert(){
        Long bno=100L;
        Board board=Board.builder().bno(bno).build();
        Reply reply= Reply.builder()
                .board(board)
                .replytext("댓글내용")
                .replyer("replyer1")
                .build();
        replyRepository.save(reply);
    }
    @Test
    public void testRepliesSelect(){
    Long bno=299L;
        Pageable pageable = PageRequest.of(0, 10,Sort.by("rno").descending());
        Page<Reply> result=replyRepository.listOdBoard(bno, pageable);
        result.forEach(reply -> {
            log.info(reply);
        });
    }

}
