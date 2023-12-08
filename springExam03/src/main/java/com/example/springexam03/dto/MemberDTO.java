package com.example.springexam03.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MemberDTO {
    private String username;
    private String password;
    private String name;
    private String nikname;
    private Date regdate;
}
