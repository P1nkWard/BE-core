package com.example.core.service;

import com.example.core.member.domain.Address;
import com.example.core.member.domain.Phone;
import com.example.core.member.dto.MemberDto;
import com.example.core.member.dto.MemberSearchSpecRequest;
import com.example.core.member.dto.RegisterDto;
import com.example.core.member.entity.Member;
import com.example.core.member.exception.MemberAlreadyExistsException;
import com.example.core.member.persistence.MemberRepository;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {
    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    private Member member;
    private List<Member> members;
    private MemberDto dto;
    private RegisterDto registerDto;

    @BeforeEach
    void setUp() {
        String id = "abc";
        String pw = "123";
        String name = "myName";
        String email = "test@test.com";
        Phone phone = new Phone("010", "0000", "0000");
        Address address = new Address("대한민국 경기도 의정부시", "경민로 00길", "401호");

        registerDto = new RegisterDto(id, pw, name, email, phone, address);
        member = Member.fromDto(registerDto);
        members = List.of(member);
        dto = new MemberDto(id, pw);
    }

    @Test
    @DisplayName(value = "회원가입 성공 테스트")
    public void registerSuccessTest() {
        when(memberRepository.findById(registerDto.getId())).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> memberService.register(registerDto));
        verify(memberRepository, times(1)).findById(registerDto.getId());
    }

    @Test
    @DisplayName(value = "회원가입 실패 테스트")
    public void registerFailTest() {
        when(memberRepository.findById(dto.getId())).thenReturn(Optional.of(member));

        Exception exception = assertThrows(MemberAlreadyExistsException.class, () -> memberService.register(registerDto));
        assertTrue(exception.getMessage().contains("이미 존재하는 아이디입니다"));
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