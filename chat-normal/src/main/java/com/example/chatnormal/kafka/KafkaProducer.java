package com.example.chatnormal.kafka;

import com.example.chatnormal.dto.ChatMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMassage(String topic, ChatMessage chatMessage) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonInString = "";
        try {
            // OrderDto 객체를 JSON 문자열로 직렬화
            jsonInString = objectMapper.writeValueAsString(chatMessage);
        } catch(JsonProcessingException e) {
            e.printStackTrace();
        }
        kafkaTemplate.send(topic, jsonInString);
    }

}