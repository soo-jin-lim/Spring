package com.example.springexam03.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class BoardDTO {//게시글의 정보를 담는 클래스, 화면에서 전달되는 데이터를 받아서 서비스로 전달
    private int bno;
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
    @NotEmpty
    private String writer;

    private int visitcount;
    @Future
    private LocalDate postdate;
    private int replycount;
}
