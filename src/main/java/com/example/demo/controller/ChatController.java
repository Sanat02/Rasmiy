package com.example.demo.controller;

import com.example.demo.dto.ChatDto;
import com.example.demo.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;
    @PostMapping("/{userId}")
    public HttpStatus addMessage(@RequestBody ChatDto chatDto) {
        chatService.saveMessage(chatDto);
        return HttpStatus.OK;
    }
}
