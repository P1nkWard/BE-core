package com.example.core.member.controller;

import com.example.core.member.controller.dto.LoginDto;
import com.example.core.member.controller.dto.MemberDto;
import com.example.core.member.controller.dto.MemberSearchSpecRequest;
import com.example.core.member.controller.dto.RegisterDto;
import com.example.core.member.service.LoginService;
import com.example.core.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("members")
@RequiredArgsConstructor
public class MemberController {
    private static String HEADER_REFERER = "referer";

    private final MemberService memberService;


    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@Valid @RequestBody RegisterDto dto, @RequestHeader(name = "referer") String referer) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HEADER_REFERER, referer);

        memberService.register(dto);

        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("id", dto.getId());
        responseBody.put("message", "회원가입 성공");

        return ResponseEntity.status(HttpStatus.CREATED).headers(httpHeaders).body(responseBody);
    }



    @GetMapping
    public ResponseEntity<List<MemberDto>> findList(@ModelAttribute MemberSearchSpecRequest searchSpec) {
        List<MemberDto> members = memberService.findList(searchSpec);

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