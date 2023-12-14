package com.example.sbjap01.controller;

import com.example.sbjap01.dto.SampleDTO;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@Log4j2
public class SampleController {
  @GetMapping("/hello")
  public void hello(Model model){
    log.info("hello..................!");
    model.addAttribute("msg", "Hello World!");
  }
  @GetMapping("/json")
  @ResponseBody
  public String[] json(){
    return new String[]{"AA", "BB", "CC"};
  }
  @GetMapping("/dto")
  @ResponseBody
  public SampleDTO dto(){
    return new SampleDTO("홍길동", 20);
  }

  @GetMapping("/dtoList")
  @ResponseBody
  public List<SampleDTO> dtoList(){
    List<SampleDTO> dtoList = new ArrayList<>();
    dtoList.add(new SampleDTO("홍길동", 20));
    dtoList.add(new SampleDTO("이길동", 21));
    dtoList.add(new SampleDTO("이길자", 22));
    return dtoList;
  }

  @GetMapping("/ex01")
  public void ex01(Model model){
    List<String> list= Arrays.asList("aa", "bb");
    model.addAttribute("list",list);
  }
  @GetMapping("/dtoList2")
  public void dtoList(Model model){
    List<SampleDTO> dtoList=new ArrayList<>();
    dtoList.add(new SampleDTO("홍길동", 20));
    dtoList.add(new SampleDTO("이길동", 21));
    dtoList.add(new SampleDTO("이길자", 22));
    model.addAttribute("dtoList",dtoList);
  }
  @GetMapping("/script")
  public void script(Model model){
    List<SampleDTO> dtoList=new ArrayList<>();
    dtoList.add(new SampleDTO("홍길동", 20));
    dtoList.add(new SampleDTO("이길동", 21));
    dtoList.add(new SampleDTO("이길자", 22));
    model.addAttribute("dtoList",dtoList);

    Map<String, SampleDTO> map=new HashMap<>();
    map.put("dto", new SampleDTO("박경미", 10));
    model.addAttribute("map",map);

    SampleDTO dto = new SampleDTO("정은주", 21);
    model.addAttribute("dto", dto);
  }
  @GetMapping("/ex02")
  public void ex03(){
  }
}
