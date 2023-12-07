package com.example.springexam03.vo;

import lombok.Data;

import java.time.LocalDate;
@Data
public class BoardVO {
    private int bno;
    private String title;
    private String content;
    private String writer;
    private int visitcount;
    private LocalDate postdate;
    private int replycount;
}
