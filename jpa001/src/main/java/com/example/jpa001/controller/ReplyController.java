package com.example.jpa001.controller;

import com.example.jpa001.dto.ReplyDTO;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/replies")
@Log4j2

public class ReplyController {
    public ResponseEntity<Map<String, Long>> register(@Valid @RequestBody ReplyDTO replyDTO, BindingResult bindingResult) throws BindException{
        log.info(replyDTO);
        if(bindingResult.hasErrors()){
            throw new BindException(bindingResult);
        }
        Map<String, Long> resultMap=Map.of("rno",111L);
        return ResponseEntity.ok(resultMap);
    }
}
