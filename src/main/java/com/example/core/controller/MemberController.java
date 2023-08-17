package com.example.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {

    @PostMapping("register")
    public String register(){
        return "Hello";
    }
}
