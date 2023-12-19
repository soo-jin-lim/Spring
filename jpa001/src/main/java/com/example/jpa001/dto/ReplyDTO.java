package com.example.jpa001.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor


public class ReplyDTO {
    private Long rno;
    private Long bno;
    private String replyText;
    private String replyer;
    private LocalDateTime regDate,modDate;


}
