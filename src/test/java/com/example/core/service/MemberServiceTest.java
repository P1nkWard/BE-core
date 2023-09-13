package com.example.core.service;

import com.example.core.member.dto.MemberDto;
import com.example.core.member.dto.MemberSearchSpecRequest;
import com.example.core.member.entity.Member;
import com.example.core.member.persistence.MemberRepository;
import com.example.core.member.service.LoginService;
import com.example.core.member.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {
    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private LoginService loginService;
    @InjectMocks
    private MemberService memberService;

    private Member member;
    private List<Member> members;
    private MemberDto dto;

    @BeforeEach
    void setUp() {
        String id = "abc";
        String pw = "123";

        member = new Member(id, pw);
        members = List.of(member);
        dto = new MemberDto(id, pw);
    }

    @Test
    @DisplayName(value = "로그인 성공 테스트")
    public void loginSuccessTest() {
        when(memberRepository.findById(dto.getId())).thenReturn(Optional.of(member));

        int result = loginService.login(dto);
        assertEquals(1, result);
    }

    @Test
    @DisplayName(value = "로그인 실패 테스트 - 존재하지 않는 아이디")
    public void loginFailTestWithNonexistentId() {
        dto.setId("nonexistentId");

        when(memberRepository.findById(dto.getId())).thenReturn(Optional.empty());

        int result = loginService.login(dto);
        assertEquals(-1, result);
    }

    @Test
    @DisplayName(value = "로그인 실패 테스트 - 비밀번호 불일치")
    public void loginFailTestWithWrongPassword() {
        dto.setPw("wrongPw");

        when(memberRepository.findById(dto.getId())).thenReturn(Optional.of(member));

        int result = loginService.login(dto);
        assertNotEquals(1, result);
    }

    @Test
    @DisplayName(value = "회원가입 성공 테스트")
    public void registerSuccessTest() {
        when(memberRepository.findById(dto.getId())).thenReturn(Optional.empty());

        boolean result = memberService.register(dto);
        assertTrue(result);
    }

    @Test
    @DisplayName(value = "회원가입 실패 테스트")
    public void registerFailTest() {
        when(memberRepository.findById(dto.getId())).thenReturn(Optional.of(member));

        boolean result = memberService.register(dto);
        assertFalse(result);
    }

    @Test
    @DisplayName(value = "회원 조회 테스트")
    public void findListTest() {
        List<String> ids = List.of("abc", "qwer");
        MemberSearchSpecRequest searchSpec = new MemberSearchSpecRequest();

        searchSpec.setIds(ids);
        when(memberRepository.findBySearchSpec(searchSpec)).thenReturn(members);

        List<MemberDto> dtos = memberService.search(searchSpec);
        List<MemberDto> expectedDtos = members.stream().map(MemberDto::new).toList();

        assertEquals(expectedDtos, dtos);
    }
}