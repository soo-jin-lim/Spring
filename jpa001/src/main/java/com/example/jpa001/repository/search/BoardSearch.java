package com.example.jpa001.repository.search;

import com.example.jpa001.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardSearch {
    Page<Board> search1(Pageable pageable);
    Page<Board> searchAll(String[] type, String keyword, Pageable pageable);
}
