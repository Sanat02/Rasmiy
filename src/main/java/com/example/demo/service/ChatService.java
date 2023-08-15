package com.example.demo.service;

import com.example.demo.dao.ChatDao;
import com.example.demo.dto.ChatDto;
import com.example.demo.model.Chat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<ChatDto> getMessagesByEmployerId(int userId, int employerId) {
        List<Chat> chats = chatDao.getMessagesByEmployerId(userId, employerId);
        return chats.stream().map(e -> ChatDto.builder()
                .message(e.getMessage())
                .employerId(e.getEmployerId())
                .userId(e.getUserId())
                .messageDate(e.getMessageDate())
                .id(e.getId())
                .build()
        ).collect(Collectors.toList());
    }
}
