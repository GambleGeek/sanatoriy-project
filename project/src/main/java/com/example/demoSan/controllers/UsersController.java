package com.example.demoSan.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UsersController {

    @GetMapping("")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String authorization(){
        return "login";
    }

    @GetMapping("/rooms")
    public String rooms() {
        return "nomera";
    }

    @GetMapping("/aboutus")
    public String aboutus() {
        return "aboutus";
    }

    @GetMapping("/procedures")
    public String procedures() {
        return "procedures";
    }

}