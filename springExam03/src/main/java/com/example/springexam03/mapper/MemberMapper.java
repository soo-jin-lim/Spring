package com.example.springexam03.mapper;

import com.example.springexam03.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
    int insertMember(MemberVO vo);
    MemberVO readMember(String username);
    List<MemberVO> getMemberList();
    int updateMember(MemberVO vo);
    int deleteMember(String username);
    int conformedUsername(String username);
    int conformedNikname(String nikname);
}
