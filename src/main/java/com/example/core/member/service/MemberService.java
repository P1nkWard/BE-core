package com.example.core.member.service;

import com.example.core.member.controller.dto.MemberDto;
import com.example.core.member.controller.dto.MemberSearchSpecRequest;
import com.example.core.member.controller.dto.RegisterDto;
import com.example.core.member.controller.dto.UpdateDto;
import com.example.core.member.domain.entity.Member;
import com.example.core.member.domain.vo.MemberSearchSpec;
import com.example.core.member.exception.MemberAlreadyExistsException;
import com.example.core.member.exception.NotFoundMemberException;
import com.example.core.member.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void register(RegisterDto dto) {
        Optional<Member> member = memberRepository.findById(dto.getId());

        if (member.isPresent())
            throw new MemberAlreadyExistsException("이미 존재하는 아이디입니다");

        memberRepository.save(dto.toEntity());
    }

    public List<MemberDto> findList(MemberSearchSpecRequest searchSpec) {
        MemberSearchSpec spec = convertDtoToDomain(searchSpec);
        List<Member> memberList = memberRepository.findBySearchSpec(spec);

        if (memberList.isEmpty())
            throw new NotFoundMemberException("검색 조건에 해당하는 회원을 찾을 수 없습니다");

        return memberList.stream().map(Member::toMemberDto).toList();
    }

    public void update(UpdateDto dto) {
        Member member = memberRepository.findById(dto.getId())
                .orElseThrow(() -> new NotFoundMemberException("수정할 회원을 찾을 수 없습니다"));

        member.update(dto.toEntity());
        memberRepository.save(member);
    }

    public void delete(String id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new NotFoundMemberException("삭제할 회원을 찾을 수 없습니다"));

        memberRepository.delete(member);
    }

    public MemberSearchSpec convertDtoToDomain(MemberSearchSpecRequest dto) {
        return new MemberSearchSpec(dto.getId(), dto.getPw(), dto.getName(), dto.getEmail(), dto.getPhone(), dto.getAddress(), dto.getCreateDate());
    }
}