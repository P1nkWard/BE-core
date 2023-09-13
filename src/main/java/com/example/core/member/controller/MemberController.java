package com.example.core.member.controller;

import com.example.core.member.dto.LoginDto;
import com.example.core.member.dto.MemberDto;
import com.example.core.member.dto.MemberSearchSpecRequest;
import com.example.core.member.service.LoginService;
import com.example.core.member.service.MemberService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("members")
@RequiredArgsConstructor
public class MemberController {
    private static String HEADER_REFERER = "referer";

    private final MemberService memberService;
    private final LoginService loginService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody @Validated MemberDto member, @RequestHeader(name = "referer") String referer, Errors errors) {
        if (errors.hasErrors()) throw new ValidationException();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HEADER_REFERER, referer);

        boolean result = memberService.register(member);
        Map<String, String> responseBody = new HashMap<>();
        int status;
        String message;

        if (result) {
            status = 200;
            message = "회원가입 성공";
            responseBody.put("id", member.getId());
        } else {
            status = 409;
            message = "회원가입 실패 - 이미 존재하는 아이디";
        }

        responseBody.put("message", message);

        return ResponseEntity.status(status).headers(httpHeaders).body(responseBody);
    }

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

    @PostMapping("inquiry")
    public ResponseEntity<List<MemberDto>> findList(@RequestBody MemberSearchSpecRequest searchSpec) {
        List<MemberDto> members = memberService.search(searchSpec);

        return ResponseEntity.ok(members);
    }

    @PutMapping
    public ResponseEntity<String> modify(@RequestBody MemberDto member) {
        // 회원 정보를 수정했다고 가정
        String message = "회원 정보 수정 성공";

        return ResponseEntity.ok(message);
    }

    @DeleteMapping("{ids}")
    public ResponseEntity<String> delete(@PathVariable List<String> ids) {
        // 회원을 삭제했다고 가정
        String message = "회원 삭제 성공";

        return ResponseEntity.ok(message);
    }

    @PostMapping("find-id")
    public ResponseEntity<String> findMemberId(@RequestBody MemberDto member) {
        // 이름과 전화번호로 회원의 아이디를 찾았다고 가정
        String userId = "찾은 아이디";

        return ResponseEntity.ok(userId);
    }

    @PostMapping("find-pw")
    public ResponseEntity<String> findMemberPw(@RequestBody MemberDto member) {
        // 아이디와 전화번호로 회원의 비밀번호를 찾았다고 가정
        String userPw = "찾은 비밀번호";

        return ResponseEntity.ok(userPw);
    }
}