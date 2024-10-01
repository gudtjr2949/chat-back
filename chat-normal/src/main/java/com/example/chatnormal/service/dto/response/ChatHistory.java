package com.example.chatnormal.service.dto.response;

import com.example.chatnormal.dto.ChatMessage;
import com.example.chatnormal.repository.entity.Chat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@ToString
@AllArgsConstructor
public class ChatHistory {
    private List<ChatMessage> message;
}
