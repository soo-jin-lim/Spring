package com.example.sb01.dto;

import lombok.Data;

import java.util.Date;

@Data
public class BoardDTO {
    private int bno;
    private String title;
    private String content;
    private String writer;
    private int visitcount;
    private Date postdate;
}
