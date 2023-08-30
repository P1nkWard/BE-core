package com.example.core.member.controller;

import com.example.core.member.dto.MasterDto;
import com.example.core.member.dto.MemberDto;
import com.example.core.member.service.MasterService;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class MasterController {

    private static String HEADER_REFERER = "referer";
    private final MasterService masterService;
    @PostMapping("/master/register")
    public ResponseEntity<String> register(@RequestBody @Validated MasterDto master,
                                           @RequestHeader(name = "referer") String referer, Errors errors) {
        if (errors.hasErrors()) throw new ValidationException();


        masterService.register(master);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HEADER_REFERER, referer);

        return ResponseEntity.status(200).headers(httpHeaders).body(master.getId());
    }

}
