package com.example.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    @PostMapping("find-id")
    public String findId() {
        return "id";
    }

    @PostMapping("find-pw")
    public String findPw() {
        return "pw";
    }

    @PostMapping("register")
    public String register() {
        return "registration";
    }

    @PostMapping("modify")
    public String modify() {
        return "modification";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable String id) {
        return "delete";
    }

    @GetMapping("check/{id}")
    public String search(@PathVariable String id) {
        return "check";
    }

    @GetMapping("payment/{id}")
    public String payment(@PathVariable String id) {
        return "payment";
    }
}
