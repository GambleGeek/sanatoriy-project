package com.example.demoSan.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AssistController {

    @GetMapping("/assistant/")
    public String assistant(){
        return "assistant";
    }

    @GetMapping("/client/")
    public String client(){
        return "client";
    }
}
