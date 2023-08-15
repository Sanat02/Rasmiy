package com.example.demo.controller;

import com.example.demo.dto.ChatDto;
import com.example.demo.dto.UserDto;
import com.example.demo.service.ChatService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;
    private final UserService userService;
    @PostMapping("/{userId}")
    public HttpStatus addMessage(@RequestBody ChatDto chatDto) {
        chatService.saveMessage(chatDto);
        return HttpStatus.OK;
    }

    @GetMapping("/{userId}")
    public List<ChatDto> getMessagesByEmployerId(@PathVariable int userId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = userService.mapToUserDto(userService.getUserByEmail(auth.getName()).orElse(null));
        System.out.println(chatService.getMessagesByEmployerId(userId,userDto.getId()).size());
        System.out.println("empId:"+userId);
        System.out.println("userId:"+userDto.getId());
        return chatService.getMessagesByEmployerId(userDto.getId(),userId);
    }

}
