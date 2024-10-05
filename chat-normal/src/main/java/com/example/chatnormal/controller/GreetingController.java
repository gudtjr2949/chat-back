package com.example.chatnormal.controller;

import com.example.chatnormal.dto.ChatMessage;
import com.example.chatnormal.service.ChatService;
import com.example.chatnormal.service.dto.response.ChatHistory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import org.springframework.messaging.handler.annotation.MessageMapping;

import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class GreetingController {

    private final ChatService chatService;
    private final SimpMessageSendingOperations template;

    @MessageMapping("/chatroom") // pub
    public ResponseEntity<Void> greeting(@RequestBody ChatMessage chatMessage) throws Exception {
        // 전처리를 거쳐도 됨. DB, Redis 저장 등등
        Long chatroomId = chatMessage.getId();
        chatMessage.setId(chatroomId);
        chatService.saveChat(chatMessage);
        template.convertAndSend("/sub/chatroom/" + chatMessage.getId(), chatMessage); // sub
        return ResponseEntity.ok().build();
    }

    @GetMapping("/chat/{chatroomId}")
    public ResponseEntity<ChatHistory> loadChatHistory(@PathVariable Long chatroomId) {
        ChatHistory chatHistory = chatService.loadChatHistory(chatroomId);
        return ResponseEntity.ok().body(chatHistory);
    }
}