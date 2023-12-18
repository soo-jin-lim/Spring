package com.example.springexam03.dto;

import com.example.springexam03.vo.BoardAttachVO;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
public class BoardDTO {
    private int bno;
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
    @NotEmpty
    private String writer;
    private int visitcount;
    private LocalDate postdate;
    private int replycount;
    private List<BoardAttachVO> attachVOList;

}
