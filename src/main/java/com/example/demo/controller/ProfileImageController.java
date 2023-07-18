package com.example.demo.controller;

import com.example.demo.dto.ProfileImageDto;
import com.example.demo.service.ProfileImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/images")
@RequiredArgsConstructor
public class ProfileImageController {
    private final ProfileImageService profileImageService;

    @GetMapping("/download/{imageId}")
    public ResponseEntity<?> downloadImage(@PathVariable int imageId){
        return profileImageService.downloadImage(imageId);
    }

    @PostMapping("/upload")
    public HttpStatus uploadImage(ProfileImageDto profileImageDto){
        profileImageService.uploadImage(profileImageDto);
        return HttpStatus.OK;

    }
}
