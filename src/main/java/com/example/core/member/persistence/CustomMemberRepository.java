package com.example.core.member.persistence;

import com.example.core.member.dto.MemberSearchSpecRequest;
import com.example.core.member.entity.Member;

import java.util.List;

public interface CustomMemberRepository {
    List<Member> findBySearchSpec(MemberSearchSpecRequest searchSpec);
}
