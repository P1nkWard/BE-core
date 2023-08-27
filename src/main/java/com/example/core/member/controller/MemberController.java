package com.example.core.member.controller;

import com.example.core.member.dto.MemberDto;
import com.example.core.member.dto.MemberSearchSpecRequest;
import com.example.core.member.service.MemberService;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("members")
@RequiredArgsConstructor
public class MemberController {
    private static String HEADER_REFERER = "referer";

    private final MemberService memberService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Validated MemberDto member, @RequestHeader(name = "referer") String referer, Errors errors) {
        if (errors.hasErrors()) throw new ValidationException();

        memberService.register(member);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HEADER_REFERER, referer);

        return ResponseEntity.status(200).headers(httpHeaders).body(member.getId());
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<?> login(@RequestParam String id, @RequestParam String pw, @RequestHeader(name = "referer") String referer) {
        MemberDto member = new MemberDto();
        member.setId(id);
        member.setPw(pw);
        memberService.login(member);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HEADER_REFERER, referer);

        return ResponseEntity.status(200).headers(httpHeaders).body(member.getId());
    }

    @GetMapping
    public ResponseEntity<List<MemberDto>> findList(MemberSearchSpecRequest searchSpec) {
        return ResponseEntity.ok(null);
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
