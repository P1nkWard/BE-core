package com.example.core.member.persistence;

import com.example.core.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, String>, CustomMemberRepository {
}