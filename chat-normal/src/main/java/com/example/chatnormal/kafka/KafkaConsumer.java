package com.example.chatnormal.kafka;

import com.example.chatnormal.dto.ChatMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    private final SimpMessagingTemplate template;

    @KafkaListener(topics = "chat-exchange")
    public void consume(String message) {
//        template.convertAndSend("/sub/chat/" + chatMessage.getId(), chatMessage);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // String 메시지를 ChatMessage 객체로 변환
            ChatMessage chatMessage = objectMapper.readValue(message, ChatMessage.class);

            // WebSocket을 통해 해당 채팅방으로 메시지 전송
            String destination = "/sub/chat/" + chatMessage.getId();
            template.convertAndSend(destination, chatMessage);
        } catch (Exception e) {
            e.printStackTrace(); // 에러 로그 출력
        }
    }
}