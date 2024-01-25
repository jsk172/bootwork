package com.khit.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class Sample2Controller {
    //문자를 전달
    @GetMapping("/sample2")
    @ResponseBody
    public String test(){
        return "안녕??";
    }
}
