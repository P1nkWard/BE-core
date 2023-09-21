package com.example.core.member.service;

import com.example.core.member.controller.dto.MemberDto;
import com.example.core.member.controller.dto.MemberSearchSpecRequest;
import com.example.core.member.controller.dto.RegisterDto;
import com.example.core.member.controller.dto.UpdateDto;
import com.example.core.member.domain.Member;
import com.example.core.member.exception.MemberAlreadyExistsException;
import com.example.core.member.exception.NotFoundMemberException;
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

        if (member.isPresent())
            throw new MemberAlreadyExistsException("이미 존재하는 아이디입니다");

        memberRepository.save(dto.toEntity());
    }

    public List<MemberDto> findList(MemberSearchSpecRequest searchSpec) {
        List<Member> memberList = memberRepository.findBySearchSpec(searchSpec);

        if (memberList.isEmpty())
            throw new NotFoundMemberException("검색 조건에 해당하는 회원을 찾을 수 없습니다");

        return memberList.stream().map(Member::toMemberDto).toList();
    }

    public void update(UpdateDto dto) {
        Optional<Member> member = memberRepository.findById(dto.getId());

        if (member.isEmpty())
            throw new NotFoundMemberException("수정할 회원을 찾을 수 없습니다");

        memberRepository.save(dto.toEntity());
    }

    public void delete(String id) {
        Optional<Member> member = memberRepository.findById(id);


        if (member.isEmpty())
            throw new NotFoundMemberException("삭제할 회원을 찾을 수 없습니다");

        memberRepository.delete(member.get());
    }
}