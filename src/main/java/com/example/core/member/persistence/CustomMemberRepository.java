package com.example.core.member.persistence;

import com.example.core.member.domain.entity.Member;
import com.example.core.member.domain.vo.MemberSearchSpec;

import java.util.List;

public interface CustomMemberRepository {
    List<Member> findBySearchSpec(MemberSearchSpec searchSpec);
}
