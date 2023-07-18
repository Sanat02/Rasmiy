package com.example.demo.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProfileImageDto {
    private int id;
    private MultipartFile file;
    private int userId;
}
