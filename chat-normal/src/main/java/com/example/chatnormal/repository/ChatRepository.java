package com.example.chatnormal.repository;

import com.example.chatnormal.repository.entity.Chat;
import com.example.chatnormal.repository.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    List<Chat> findChatsByChatRoom(ChatRoom chatRoom);
}
