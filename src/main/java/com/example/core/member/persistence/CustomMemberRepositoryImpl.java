package com.example.core.member.persistence;

import com.example.core.member.domain.entity.Member;
import com.example.core.member.domain.vo.MemberSearchSpec;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomMemberRepositoryImpl implements CustomMemberRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Member> findBySearchSpec(MemberSearchSpec searchSpec) {
        return null;
    }
}