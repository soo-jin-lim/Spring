package com.example.springexam03.controller;

import com.example.springexam03.dto.ReplyDTO;
import com.example.springexam03.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/replies")
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyService replyService;

    @PostMapping("/new")
    public ResponseEntity<String> create(@RequestBody ReplyDTO dto){
        log.info(dto);
        int result=replyService.register(dto);
        log.info(result);
        return result==1? new ResponseEntity<>("success", HttpStatus.OK)
                :new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @GetMapping("/list/{bno}")
    public ResponseEntity<List<ReplyDTO>> getList(@PathVariable("bno") int bno){
        log.info(bno);
        List<ReplyDTO> dtoList=replyService.getList(bno);
        ResponseEntity<ReplyDTO> result=null;
        //result=ResponseEntity.status(HttpStatus.OK).body(dtoList);
        return new ResponseEntity<>(dtoList,HttpStatus.OK);
    }

    @GetMapping("/{rno}")
    public ResponseEntity<ReplyDTO> get(@PathVariable("rno") int rno){
        log.info(rno);
        return new ResponseEntity<>(replyService.read(rno),HttpStatus.OK);
    }
    @DeleteMapping("/{rno}")
    private ResponseEntity<String> remove(@PathVariable("rno") int rno){
        log.info(rno);
        int result=replyService.remove(rno);
        return result==1?new ResponseEntity<>("success",HttpStatus.OK):
                new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PutMapping(value = "/{rno}")
    public ResponseEntity<String> modify(@RequestBody ReplyDTO dto,
                                         @PathVariable("rno") int rno){
        dto.setRno(rno);
        log.info(dto);
        int result=replyService.modify(dto);
        return result==1? new ResponseEntity<>("success",HttpStatus.OK):
                new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
