package com.example.springexam03.service;

import com.example.springexam03.dto.MemberDTO;
import com.example.springexam03.mapper.MemberMapper;
import com.example.springexam03.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    private final ModelMapper modelMapper;
    private final MemberMapper memberMapper;

    @Override
    public int register(MemberDTO dto) {
        MemberVO vo=modelMapper.map(dto,MemberVO.class);
        return memberMapper.insertMember(vo);
    }
    @Override
    public MemberDTO get(String username) {
        MemberDTO dto=modelMapper
                .map(memberMapper.readMember(username),MemberDTO.class);
        return dto;
    }

    @Override
    public List<MemberDTO> getList() {
        List<MemberDTO> dtoList=memberMapper.getMemberList().stream()
                .map(vo->modelMapper.map(vo,MemberDTO.class))
                .collect(Collectors.toList());
        return dtoList;
    }



    @Override
    public int modify(MemberDTO dto) {
        MemberVO vo=modelMapper.map(dto,MemberVO.class);
        return memberMapper.updateMember(vo);
    }

    @Override
    public int remove(String username) {
        return memberMapper.deleteMember(username);
    }

    @Override
    public int conformedUsername(String username) {
        log.info(username);
        int count=memberMapper.conformedUsername(username);
        log.info(count+"~~~~~~~~~~~~~~~~~~~~");
        return count;
    }

    @Override
    public int conformedNikname(String nikname) {
        log.info(nikname);
        return memberMapper.conformedNikname(nikname);
    }

    @Override
    public MemberDTO loginPro(String username, String password) {
        MemberVO vo=memberMapper.readMember(username);
        if(vo.getPassword().equals(password)){
            MemberDTO dto=modelMapper.map(vo, MemberDTO.class);
            return dto;
        }
        return null;
    }


}
