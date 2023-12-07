package com.example.springexam03.vo;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class MemberVO {
    private String username;
    private String password;
    private String name;
    private String nikname;
    private Date regdate;
}

