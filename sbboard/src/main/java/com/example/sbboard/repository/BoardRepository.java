package com.example.sbboard.repository;

import com.example.sbboard.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board,Long> {
    List<Board> findByWriter(String name);

    //@Query
    //Board getBoard(Long bno);
    @Modifying
    @Query("update Board b set b.visitcount=b.visitcount+1 where b.bno =: bno")
    //@Query(value="update Board b set b.visitcount=b.visitcount+1 where b.bno=:bno", nativeQuery = true)
    void updateByVisitcount(@Param("bno") Long bno);

    @Query("select b from Board b where b.content like %:keyword% " +
            "order by b.bno desc")
    List<Board> findByContentLike(@Param("keyword") String keyword);
    List<Board> findByContentContaining(String keyword);
    //List<Board> findByTitleOrContentContains(String keyword);

    //public List<Board> getByContentLike(String keyword);


}
