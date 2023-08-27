package com.example.core.controller;

import com.example.core.MemberDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("member")
public class MemberController {
    @PostMapping("")
    public ResponseEntity<String> registerMember(@RequestBody MemberDto member) {
        // 회원 정보를 등록했다고 가정
        String message = "회원 등록 성공";

        return ResponseEntity.ok(message);
    }

    @GetMapping("{ids}")
    public ResponseEntity<List<MemberDto>> checkMember(@PathVariable List<String> ids) {
        // 회원을 조회했다고 가정
        List<MemberDto> list = new ArrayList<>();
        for (String id : ids) {
            MemberDto member = new MemberDto();
            member.setId(id);
            member.setPw("123");
            member.setName("myName");
            member.setPhone("010-0000-0000");
            member.setAddress("myAddress");
            list.add(member);
        }

        return ResponseEntity.ok(list);
    }

    @PutMapping("")
    public ResponseEntity<String> modifyMember(@RequestBody MemberDto member) {
        // 회원 정보를 수정했다고 가정
        String message = "회원 정보 수정 성공";

        return ResponseEntity.ok(message);
    }

    @DeleteMapping("{ids}")
    public ResponseEntity<String> deleteMember(@PathVariable List<String> ids) {
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

//    @PostMapping("login")
//    @ResponseBody
//    public LoginSuccesssResponse login(MemberDto member) {
//        LoginSuccesssResponse ls = new LoginSuccesssResponse();
//        ls.status = true;
//        ls.authTime = "2023/08/24";
//        ls.loginUserId = member.getId();
//
//        return ls;
//    }
//
//    class LoginSuccesssResponse {
//
//        boolean status;
//
//        String authTime;
//
//        String loginUserId;
//
//        String previousLocation;
//
//    }
}
