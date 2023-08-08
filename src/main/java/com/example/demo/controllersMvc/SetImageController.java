package com.example.demo.controllersMvc;

import com.example.demo.dto.ProfileImageDto;
import com.example.demo.dto.UserDto;
import com.example.demo.enums.AccountType;
import com.example.demo.service.ProfileImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RequiredArgsConstructor
@RequestMapping("/image")
@Controller
public class SetImageController {
    private final ProfileImageService profileImageService;

    @GetMapping("/{userId}")
    public String setImage(@PathVariable int userId, Model model) {
        model.addAttribute("userId", userId);
        return "setImage";
    }

    @PostMapping("/{userId}")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String addImage(
            @RequestParam(name = "files") MultipartFile image,
            @PathVariable int userId

    ) {
        ProfileImageDto profileImageDto=ProfileImageDto.builder()
                .file(image)
                .userId(userId)
                .build();
        profileImageService.uploadImage(profileImageDto);
        return "redirect:/profile/" + userId;

    }

}
