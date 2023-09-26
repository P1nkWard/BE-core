package com.example.core.service;

import com.example.core.member.domain.vo.Address;
import com.example.core.member.domain.vo.MemberSearchSpec;
import com.example.core.member.domain.vo.Phone;
import com.example.core.member.controller.dto.MemberDto;
import com.example.core.member.controller.dto.MemberSearchSpecRequest;
import com.example.core.member.controller.dto.RegisterDto;
import com.example.core.member.domain.entity.Member;
import com.example.core.member.exception.MemberAlreadyExistsException;
import com.example.core.member.exception.NotFoundMemberException;
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

    @BeforeEach
    void setUp() {
        Phone phone = new Phone("010", "0000", "0000");
        Address address = new Address("대한민국 경기도 의정부시", "경민로 00길", "401호");
        member = new Member("abc", "123", "myName", "test@test.com", phone, address, null);
    }

    @Test
    @DisplayName(value = "회원가입 성공 테스트")
    public void registerSuccessTest() {
        RegisterDto dto = member.toRegisterDto();

        when(memberRepository.findById(dto.getId())).thenReturn(Optional.empty());

        assertDoesNotThrow(() -> memberService.register(dto));
        verify(memberRepository, times(1)).findById(dto.getId());
    }

    @Test
    @DisplayName(value = "회원가입 실패 테스트")
    public void registerFailTest() {
        RegisterDto dto = member.toRegisterDto();

        when(memberRepository.findById(dto.getId())).thenReturn(Optional.of(member));

        assertThrows(MemberAlreadyExistsException.class, () -> memberService.register(dto));
    }

    @Test
    @DisplayName(value = "회원 조회 성공 테스트")
    public void findListSuccessTest() {
        MemberSearchSpecRequest searchSpecRequest = new MemberSearchSpecRequest();
        searchSpecRequest.setName("myName");
        MemberSearchSpec searchSpec = memberService.convertDtoToDomain(searchSpecRequest);

        when(memberRepository.findBySearchSpec(searchSpec)).thenReturn(List.of(member));

        List<MemberDto> dtos = memberService.findList(searchSpecRequest);
        assertEquals(List.of(member.toMemberDto()), dtos);
    }

    @Test
    @DisplayName(value = "회원 조회 실패 테스트")
    public void findListFailTest() {
        MemberSearchSpecRequest searchSpecRequest = new MemberSearchSpecRequest();
        searchSpecRequest.setName("myName");
        MemberSearchSpec searchSpec = memberService.convertDtoToDomain(searchSpecRequest);

        when(memberRepository.findBySearchSpec(searchSpec)).thenReturn(List.of());

        assertThrows(NotFoundMemberException.class, () -> memberService.findList(searchSpecRequest));
    }
}