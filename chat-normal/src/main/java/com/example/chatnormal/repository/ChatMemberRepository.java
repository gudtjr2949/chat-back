package com.example.chatnormal.repository;

import com.example.chatnormal.repository.entity.ChatMember;
import com.example.chatnormal.repository.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMemberRepository extends JpaRepository<ChatMember, Long> {
    List<ChatMember> findChatMemberByMember(Member member);
}
