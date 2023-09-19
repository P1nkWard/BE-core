package com.example.core.member.persistence;

import com.example.core.member.dto.MemberSearchSpecRequest;
import com.example.core.member.entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomMemberRepositoryImpl implements CustomMemberRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Member> findBySearchSpec(MemberSearchSpecRequest searchSpec) {
        return null;
    }
}