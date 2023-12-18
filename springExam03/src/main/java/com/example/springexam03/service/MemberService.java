package com.example.springexam03.service;

import com.example.springexam03.dto.MemberDTO;

import java.util.List;

public interface MemberService {
    int register(MemberDTO dto);
    List<MemberDTO> getList();
    MemberDTO get(String username);
    int modify(MemberDTO dto);
    int remove(String username);
    int conformedUsername(String username);
    int conformedNikname(String nikname);

    MemberDTO loginPro(String username, String password);
}
