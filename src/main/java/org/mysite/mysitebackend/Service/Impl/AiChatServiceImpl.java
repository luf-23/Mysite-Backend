package org.mysite.mysitebackend.Service.Impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mysite.mysitebackend.Service.AiChatService;
import org.mysite.mysitebackend.entity.AIRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AiChatServiceImpl implements AiChatService {

    private static final String DEFAULT_MODEL = "deepseek-v3";
    @Value("${ai.key}")
    private String API_KEY;
    @Value("${ai.url}")
    private String API_URL;
    //流式
    private final WebClient webClient;
    private final ObjectMapper objectMapper;
    //普通
    private final RestTemplate restTemplate;

    public AiChatServiceImpl(RestTemplate restTemplate,WebClient aiWebClient, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.webClient = aiWebClient;
        this.objectMapper = objectMapper;
    }

    @Override
    public String chatWithAI(List<AIRequest.Message> messages, String model) {
        // 1. 准备请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + API_KEY);

        // 2. 构建符合DashScope要求的请求体
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", model != null ? model : DEFAULT_MODEL);

        // 转换消息格式
        List<Map<String, String>> apiMessages = messages.stream()
                .map(msg -> {
                    Map<String, String> m = new HashMap<>();
                    m.put("role", msg.getRole());
                    m.put("content", msg.getContent());
                    return m;
                })
                .collect(Collectors.toList());

        requestBody.put("messages", apiMessages);
        requestBody.put("temperature", 0.4);

        // 3. 发送请求
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<Map> response = restTemplate.exchange(
                API_URL,
                HttpMethod.POST,
                requestEntity,
                Map.class
        );

        // 4. 解析响应
        // 修改后的解析逻辑
        if (response.getStatusCode() == HttpStatus.OK) {
            Map<String, Object> responseBody = response.getBody();
            if (responseBody != null && responseBody.containsKey("choices")) {
                // 安全类型转换
                List<Map<String, Object>> choices = (List<Map<String, Object>>) responseBody.get("choices");
                if (!choices.isEmpty()) {
                    Map<String, Object> firstChoice = choices.get(0);
                    Map<String, Object> message = (Map<String, Object>) firstChoice.get("message");
                    return (String) message.get("content"); // 提取助手的回复内容
                }
            }
        }
        throw new RuntimeException("无法解析AI响应: " + response.getBody());
    }

    @Override
    public SseEmitter handleStreamRequest(AIRequest request) {
        SseEmitter emitter = new SseEmitter(180_000L); // 3分钟超时

        // 构建AI请求体
        Map<String, Object> requestBody = Map.of(
                "model", request.getModel(),
                "messages", request.getMessages(),
                "stream", true,
                "temperature", request.getTemperature()
        );

        // 发送流式请求
        Flux<String> responseFlux = webClient.post()
                .bodyValue(requestBody)
                .retrieve()
                .bodyToFlux(String.class);

        // 处理流式响应
        responseFlux.subscribe(
                chunk -> processChunk(chunk, emitter),
                emitter::completeWithError,
                emitter::complete
        );

        return emitter;
    }
    private void processChunk(String chunk, SseEmitter emitter) {
        try {
            // 直接通过 emitter 发送原始 chunk 数据
            emitter.send(chunk);
        } catch (Exception e) {
            emitter.completeWithError(e);
        }
    }

}