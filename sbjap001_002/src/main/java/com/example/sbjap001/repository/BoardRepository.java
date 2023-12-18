package com.example.sbjap001.repository;

import com.example.sbjap001.domain.Board;
import com.example.sbjap001.repository.search.BoardSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board,Long>, BoardSearch {

    List<Board> findByWriter(String name);

    Page<Board> findByTitleContainingOrderByBnoDesc(String keyword, Pageable pageable);

    @Query("select b from Board b where b.title like concat('%',:keyword,'%') order by b.bno desc")
    Page<Board> findBykeyword(String keyword, Pageable pageable);
}
