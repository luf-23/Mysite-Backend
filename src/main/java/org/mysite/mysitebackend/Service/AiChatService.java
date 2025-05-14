package org.mysite.mysitebackend.Service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.mysite.mysitebackend.entity.AIRequest;
import org.mysite.mysitebackend.entity.Result;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

public interface AiChatService {

    String chatWithAI(List<AIRequest.Message> messages, String model);


    SseEmitter handleStreamRequest(@Valid AIRequest request);
}
