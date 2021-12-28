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
        return "home";
    }

    @GetMapping("/login")
    public String authorization(){
        return "login";
    }

    @GetMapping("/login-error")
    public String authorization_error(Model model){
        model.addAttribute("loginError", true);
        return "login";
    }

    @GetMapping("/rooms")
    public String rooms() {
        return "nomera";
    }
    @GetMapping("/room1")
    public String room1(){
        return "pages/portfolio-details1";
    }
    @GetMapping("/room2")
    public String room2(){
        return "pages/portfolio-details2";
    }
    @GetMapping("/room3")
    public String room3(){
        return "pages/portfolio-details3";
    }
    @GetMapping("/room4")
    public String room4(){
        return "pages/portfolio-details4";
    }
    @GetMapping("/room5")
    public String room5(){
        return "pages/portfolio-details5";
    }

    @GetMapping("/procedure1")
    public String procedure1(){
        return "pages/portfolio-details-acupuncture";
    }
    @GetMapping("/procedure2")
    public String procedure2(){
        return "pages/portfolio-details-herbal";
    }
    @GetMapping("/procedure3")
    public String procedure3(){
        return "pages/portfolio-details-phototherapy";
    }
    @GetMapping("/procedure4")
    public String procedure4(){
        return "pages/portfolio-details-physiotherapy";
    }
    @GetMapping("/procedure5")
    public String procedure5(){
        return "pages/portfolio-details-massage";
    }
    @GetMapping("/procedure6")
    public String procedure6(){
        return "pages/portfolio-details-mud";
    }
    @GetMapping("/procedure7")
    public String procedure7(){
        return "pages/portfolio-details-hydromassage";
    }
    @GetMapping("/procedure8")
    public String procedure8(){
        return "pages/portfolio-details-baths";
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