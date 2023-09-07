package com.example.core.member.service;

import com.example.core.member.dto.MemberDto;
import com.example.core.member.dto.MemberSearchSpecRequest;
import com.example.core.member.entity.Member;
import com.example.core.member.persistence.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public boolean register(MemberDto dto) {
        Optional<Member> member = memberRepository.findById(dto.getId());
        boolean result;

        if (member.isPresent()) {
            result = false;
        } else {
            result = true;
            memberRepository.save(new Member(dto));
        }

        return result;
    }

    public List<MemberDto> search(MemberSearchSpecRequest searchSpec) {
        List<Member> memberList = memberRepository.findBySearchSpec(searchSpec);

        return memberList.stream().map(MemberDto::new).toList();
    }
}
