# 채팅서버 - 백엔드

</br>
</br>

## 개요
Kafka를 사용한 채팅 서버중 백엔드를 구현한 프로젝트입니다.

</br>
</br>

## 소프트웨어 아키텍처

<img width="1014" alt="스크린샷 2025-01-13 오후 3 34 31" src="https://github.com/user-attachments/assets/b2fe0104-8147-401a-942a-4dc23df4169c" />

</br>

</br>
</br>

## 주요 기능

</br>

### 1. 채팅방 입장 & 채팅 전송 및 수신

채팅방에 입장하면, 해당 채팅방에 속한 사용자끼리 채팅을 주고 받습니다.

</br>

![화면 기록 2024-10-03 오후 8 41 45](https://github.com/user-attachments/assets/d75a1fac-f939-4cd9-b711-36286bb3b2cb)

<br>
<br>

## 사용 기술

<br>

### 1. Spring Cloud Gateway를 사용한 로드밸런싱

Spring Cloud Gateway를 사용해 채팅용 백엔드 서버에 로드밸런싱을 진행했습니다.


<br>
<br>

### 2. Kafka를 사용한 채팅 기능 구현

메시지 브로커로 Kafka를 사용해 다중서버에서도 사용 가능한 채팅 기능을 구현하였습니다.

<br>
<br>

### 3. Spring Cloud & Kafka를 사용한 성능 향상

Spring Cloud & Kafka을 적용해 채팅 성능을 향상시켰습니다. 

<br>

**단일 서버 채팅 성능 테스트**
![before_msa](https://github.com/user-attachments/assets/a2f7bec9-3945-4937-ab53-59f90b694dfa)


<br>

**다중 서버 채팅 성능 테스트**
![after_msa](https://github.com/user-attachments/assets/0871ac0b-7db9-4c63-b81b-42250e411c2c)

<br>
<br>

## 예시 코드


<br>

### Kafka 채팅 구현 코드


```java
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
```

```java
@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    private final SimpMessagingTemplate template;

    @KafkaListener(topics = "chat-exchange")
    public void consume(String message) {
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
```

- KafkaProducer 클래스를 통해 채팅 교환 토픽의 메시지를 추가합니다.

- KafkaConsumer 클래스를 통해 채팅 교환 토픽에 해당하는 메시지가 들어온다면, 해당 메시지의 채팅방 정보와 전송자, 메시지 내용을 다른 구독자들에게 전달합니다.
