package com.example.chatnormal.repository;

import com.example.chatnormal.repository.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findMemberByMemberId(Long memberId);
}
