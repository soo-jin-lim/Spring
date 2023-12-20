package com.example.sbjap001.controller;

import com.example.sbjap001.dto.PageRequestDTO;
import com.example.sbjap001.dto.PageResponseDTO;
import com.example.sbjap001.dto.ReplyDTO;
import com.example.sbjap001.service.ReplyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/replies")
@Log4j2
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Long> register(@Valid @RequestBody ReplyDTO replyDTO,
                                                      BindingResult bindingResult)
            throws BindException {

        log.info(replyDTO);
        if(bindingResult.hasErrors()){
            throw new BindException(bindingResult);
        }
        Map<String, Long> resultMap=new HashMap<>();
        Long rno=replyService.register(replyDTO);
        resultMap.put("rno",rno);
        return resultMap;
    }

    @GetMapping("/list/{bno}")
    public PageResponseDTO<ReplyDTO> getList(@PathVariable("bno") Long bno,
                                             PageRequestDTO pageRequestDTO){
        PageResponseDTO<ReplyDTO> responseDTO=replyService.getListOfBoard(bno,pageRequestDTO);
        return responseDTO;
    }

    @GetMapping("/{rno}")
    public ReplyDTO getReplyDTO( @PathVariable("rno") Long rno ){
        ReplyDTO replyDTO = replyService.read(rno);
        return replyDTO;
    }

    @PutMapping(value = "/{rno}", consumes = MediaType.APPLICATION_JSON_VALUE )
    public Map<String,Long> modify( @PathVariable("rno") Long rno,
                                    @RequestBody ReplyDTO replyDTO ){
        replyDTO.setRno(rno); //번호를 일치시킴
        replyService.modify(replyDTO);
        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("댓글 수정", rno);
        return resultMap;
    }

    @DeleteMapping("/{rno}")
    public Map<String, Long> remove(@PathVariable("rno") Long rno){
        replyService.remove(rno);
        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("댓글 삭제", rno);
        return resultMap;
    }

}
