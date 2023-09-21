package com.example.core.member.persistence;

import com.example.core.member.controller.dto.MemberSearchSpecRequest;
import com.example.core.member.domain.Member;

import java.util.List;

public interface CustomMemberRepository {
    List<Member> findBySearchSpec(MemberSearchSpecRequest searchSpec);
}
