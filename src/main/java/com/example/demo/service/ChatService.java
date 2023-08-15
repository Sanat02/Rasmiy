package com.example.demo.service;

import com.example.demo.dao.ChatDao;
import com.example.demo.dto.ChatDto;
import com.example.demo.model.Chat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatService {
    private final ChatDao chatDao;


    public int saveMessage(ChatDto chatDto) {
        Chat chat = Chat.builder()
                .message(chatDto.getMessage())
                .employerId(chatDto.getEmployerId())
                .userId(chatDto.getUserId())
                .messageDate(LocalDate.now())
                .build();
        int chatId = chatDao.save(chat);
        return chatId;
    }
}
