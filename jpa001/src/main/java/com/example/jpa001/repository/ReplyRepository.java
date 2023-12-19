package com.example.jpa001.repository;

import com.example.jpa001.domain.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    @Query("select r from Reply r where r.board.bno=:bno")//특정 bno인 댓글을 가져와라
    Page<Reply> listOdBoard(Long bno, Pageable pageable);

}
