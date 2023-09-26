package com.example.core.member.controller;

import com.example.core.member.controller.dto.LoginDto;
import com.example.core.member.service.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class LoginApi {
    private static String HEADER_REFERER = "referer";
    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@Valid @RequestBody LoginDto dto, @RequestHeader(name = "referer") String referer) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HEADER_REFERER, referer);

        loginService.login(dto);

        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("id", dto.getId());
        responseBody.put("message", "로그인 성공");

        /*
        // 가정: createToken 메소드가 액세스 토큰과 리프레시 토큰을 생성합니다.
        TokenDto tokenDto = createToken(dto);
        responseBody.put("accessToken", tokenDto.getAccessToken());
        responseBody.put("refreshToken", tokenDto.getRefreshToken());
        responseBody.put("expiresIn", tokenDto.getExpiresIn());
        // RESTful API를 설계할 때는 주로 토큰 기반 인증을 사용함. 이는 REST 원칙 중 하나인 무상태성을 준수하기 위함
        // 세션 기반 인증은 서버가 클라이언트의 상태(즉, 세션)를 저장하므로 RESTful API의 무상태성 원칙을 위반함
        */

        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(responseBody);
    }
}
