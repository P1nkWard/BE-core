package com.example.core.member.service;

import com.example.core.master.entity.Master;
import com.example.core.member.dto.MemberDto;
import com.example.core.member.entity.Member;
import com.example.core.member.exception.MemberAlreadyExistsException;
import com.example.core.member.exception.NotFoundMemberException;
import com.example.core.member.persistence.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberServicePractice implements ServiceCRUD<MemberDto>{
    @Autowired
    MemberRepository memberRepository;

    public MemberDto create(MemberDto memberDto){
        Optional<Member> member = memberRepository.findById(memberDto.getId());

        member.ifPresent(m -> {
            throw new MemberAlreadyExistsException("이미 존재하는 아이디입니다");
        });

//        memberRepository.save(Member.fromDto(memberDt o));
//        이거 빨간줄 생겨서 일단 주석처리함 RegisterDto 처리 어떻게 할지
//        memberRepository.save(new Member(memberDto));
        // 이 코드로 바꿈
        return memberDto;
    }
    public MemberDto modify(MemberDto memberDto){
        Optional<Member> modifyMember = memberRepository.findById(memberDto.getId());
        if(modifyMember.isPresent()){
//            Member updateMember = new Member(memberDto);
//            memberRepository.save(updateMember);
            return memberDto;
        }else{
            throw new NotFoundMemberException("수정하실 멤버가 없습니다.");
        }

    }
    // 추후 연관관계 설정이 끝나면 변경될 수 있음
    public void remove(MemberDto memberDto){
        Optional<Member> removeMember = memberRepository.findById(memberDto.getId());
        if(removeMember.isPresent()){
            memberRepository.delete(removeMember.get());
        }else{
            throw new NotFoundMemberException("삭제하실 멤버가 없습니다.");
        }
    }

    public MemberDto find(MemberDto memberDto){
        // search랑 무슨차이일까 ? 채워넣기
        return memberDto;
    }
}
