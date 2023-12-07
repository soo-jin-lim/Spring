package com.example.springexam03.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

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
    @Future
    private LocalDate postdate;
    private int replycount;
}
