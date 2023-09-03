package com.example.core.member.service;

import com.example.core.member.dto.MemberDto;
import com.example.core.member.entity.Member;
import com.example.core.member.persistence.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {
    private MemberRepository memberRepository;

    public void register(MemberDto dto) {
    }

    public boolean login(MemberDto dto) {
        Optional<Member> member = memberRepository.findById(dto.getId());
        boolean result = false;

        if(member.isPresent())
            result = member.get().getPw().equals(dto.getPw());

        return result;
    }
}
