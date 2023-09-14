package com.example.core.master.controller;

import com.example.core.master.dto.MasterDto;
import com.example.core.master.service.MasterDefaultService;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("master")
public class MasterController {

    private static String HEADER_REFERER = "referer";
    private final MasterDefaultService masterService;
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Validated MasterDto master,
                                           @RequestHeader(name = "referer") String referer, Errors errors) {
        if (errors.hasErrors()) throw new ValidationException();
        masterService.masterRegister(master);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HEADER_REFERER, referer);

        return ResponseEntity.status(200).headers(httpHeaders).body(master.getMasterId());
    }

}
