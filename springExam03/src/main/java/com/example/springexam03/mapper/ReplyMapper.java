package com.example.springexam03.mapper;

import com.example.springexam03.vo.ReplyVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReplyMapper {
    public int insertReply(ReplyVO vo);
    public ReplyVO readReply(int rno);
    public int updateReply(ReplyVO vo);
    public int deleteReply(int rno);
    public List<ReplyVO> getReplyList(int bno);

}
