package com.example.core.service;

import com.example.core.member.dto.LoginDto;
import com.example.core.member.exception.InvalidCredentialsException;
import com.example.core.member.persistence.MemberRepository;
import com.example.core.member.service.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class LoginServiceTest {
    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private LoginService loginService;

    private LoginDto dto;

    @BeforeEach
    void setUp() {
        dto = new LoginDto("abc", "123");
    }

    @Test
    @DisplayName(value = "로그인 성공 테스트")
    public void loginSuccessTest() {
        when(memberRepository.findById(dto.getId())).thenReturn(Optional.of(dto.toEntity()));

        assertDoesNotThrow(() -> loginService.login(dto));
        verify(memberRepository, times(1)).findById(dto.getId());
    }

    @Test
    @DisplayName(value = "로그인 실패 테스트 - 존재하지 않는 아이디")
    public void loginFailTestWithNonexistentId() {
        dto.setId("nonexistentId");

        when(memberRepository.findById(dto.getId())).thenReturn(Optional.empty());

        assertThrows(InvalidCredentialsException.class, () -> loginService.login(dto));
    }

    @Test
    @DisplayName(value = "로그인 실패 테스트 - 비밀번호 불일치")
    public void loginFailTestWithWrongPassword() {
        dto.setPw("wrongPw");

        when(memberRepository.findById(dto.getId())).thenReturn(Optional.of(dto.toEntity()));

        assertThrows(InvalidCredentialsException.class, () -> loginService.login(dto));
    }
}