package com.example.core.member.service;

import com.example.core.member.dto.MemberDto;
import com.example.core.member.dto.MemberSearchSpecRequest;
import com.example.core.member.dto.RegisterDto;
import com.example.core.member.entity.Member;
import com.example.core.member.exception.MemberAlreadyExistsException;
import com.example.core.member.persistence.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void register(RegisterDto dto) {
        Optional<Member> member = memberRepository.findById(dto.getId());

        member.ifPresent(m -> {
            throw new MemberAlreadyExistsException("이미 존재하는 아이디입니다");
        });

        memberRepository.save(Member.fromDto(dto));
    }

    public List<MemberDto> search(MemberSearchSpecRequest searchSpec) {
        List<Member> memberList = memberRepository.findBySearchSpec(searchSpec);

        return memberList.stream().map(MemberDto::new).toList();
    }
}
