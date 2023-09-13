package com.example.core.member.service;

import com.example.core.member.dto.MemberDto;
import com.example.core.member.entity.Member;
import com.example.core.member.persistence.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
public class LoginService {
    @Autowired
    private MemberRepository memberRepository;

    public int login(MemberDto dto) {
        Optional<Member> member = memberRepository.findById(dto.getId());
        int result;

        if(member.isEmpty())
            result = -1;
        else if (member.get().getPw().equals(dto.getPw()))
            result = 1;
        else
            result = 0;

        return result;
    }
}
