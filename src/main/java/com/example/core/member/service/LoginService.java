package com.example.core.member.service;

import com.example.core.member.controller.dto.LoginDto;
import com.example.core.member.domain.entity.Member;
import com.example.core.member.exception.InvalidCredentialsException;
import com.example.core.member.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository memberRepository;

    public void login(LoginDto dto) {
        Optional<Member> member = memberRepository.findById(dto.getId());

        if (member.isEmpty() || !member.get().getPw().equals(dto.getPw()))
            throw new InvalidCredentialsException("아이디 또는 비밀번호가 잘못되었습니다");
    }
}