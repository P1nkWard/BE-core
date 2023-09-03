package com.example.core.service;

import com.example.core.member.dto.MemberDto;
import com.example.core.member.entity.Member;
import com.example.core.member.persistence.MemberRepository;
import com.example.core.member.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {
    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    @Test
    @DisplayName(value = "로그인 성공 테스트")
    public void loginSuccessTest() {
        String id = "abc";
        String pw = "123";
        Member member = new Member(id, pw);
        MemberDto dto = new MemberDto(id, pw);

        when(memberRepository.findById(id)).thenReturn(Optional.of(member));

        boolean result = memberService.login(dto);
        assertTrue(result);
    }

    @Test
    @DisplayName(value = "로그인 실패 테스트")
    public void loginFailTest() {
        String id = "abc";
        Member member = new Member(id, "123");
        MemberDto dto = new MemberDto(id, "wrong pw");

        when(memberRepository.findById(id)).thenReturn(Optional.of(member));

        boolean result = memberService.login(dto);
        assertFalse(result);
    }
}
