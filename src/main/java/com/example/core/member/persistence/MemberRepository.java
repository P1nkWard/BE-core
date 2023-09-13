package com.example.core.member.persistence;

import com.example.core.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, String>, CustomMemberRepository {
    Optional<Member> findById(String id);
}
