package com.example.demo.controllersMvc;

import com.example.demo.dto.ProfileImageDto;
import com.example.demo.dto.UserDto;
import com.example.demo.enums.AccountType;
import com.example.demo.service.ProfileImageService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RequiredArgsConstructor
@RequestMapping("/images")
@Controller
public class SetImageController {
    private final ProfileImageService profileImageService;
    private final UserService userService;

    @GetMapping()
    public String setImage(Model model) {
        return "setImage";
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String addImage(
            @RequestParam(name = "files") MultipartFile image

    ) {
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        ProfileImageDto profileImageDto=ProfileImageDto.builder()
                .file(image)
                .userId(userService.mapToUserDto(userService.getUserByEmail(auth.getName()).get()).getId())
                .build();
        profileImageService.uploadImage(profileImageDto);
        return "redirect:/profile" ;
    }

}
