package com.example.core;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Hello {
    @GetMapping("/hello")
    @ResponseBody
    public String hello(@RequestParam("name") String name){
        return name + "hello";
    }

}
