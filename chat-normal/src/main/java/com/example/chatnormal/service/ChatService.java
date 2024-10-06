package com.example.chatnormal.service;

import com.example.chatnormal.dto.ChatMessage;
import com.example.chatnormal.dto.ChatRoomList;
import com.example.chatnormal.repository.ChatMemberRepository;
import com.example.chatnormal.repository.ChatRepository;
import com.example.chatnormal.repository.ChatRoomRepository;
import com.example.chatnormal.repository.MemberRepository;
import com.example.chatnormal.repository.entity.Chat;
import com.example.chatnormal.repository.entity.ChatMember;
import com.example.chatnormal.repository.entity.ChatRoom;
import com.example.chatnormal.repository.entity.Member;
import com.example.chatnormal.service.dto.response.ChatHistory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMemberRepository chatMemberRepository;

    public void saveChat(ChatMessage chatMessage) {
        ChatRoom chatRoom = chatRoomRepository.findById(chatMessage.getId()).get();
        chatRepository.save(Chat.builder()
                .chatRoom(chatRoom)
                .name(chatMessage.getName())
                .message(chatMessage.getMessage())
                .build());
    }

    public ChatHistory loadChatHistory(Long chatroomId) {
        ChatRoom chatRoom = chatRoomRepository.findById(chatroomId).get();
        List<Chat> messages = chatRepository.findChatsByChatRoom(chatRoom);
        List<ChatMessage> chatMessages = new ArrayList<>();

        for (Chat chat : messages) {
            chatMessages.add(new ChatMessage(chat.getChatId(), chat.getName(), chat.getMessage()));
        }

        return new ChatHistory(chatMessages);
    }
}
