package org.mysite.mysitebackend.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class AIRequest {
    @NotNull
    private List<Message> messages;  // 历史消息列表

    private String model;           // 可选，默认模型

    private Double temperature;

    @Data
    public static class Message {
        @NotBlank
        private String role;       // "user" 或 "assistant"

        @NotBlank
        private String content;     // 消息内容
    }
}