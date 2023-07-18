package com.example.demo.service;

import com.example.demo.dao.ProfileImageDao;
import com.example.demo.dto.ProfileImageDto;
import com.example.demo.model.ProfileImage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileImageService {
    private static final String SUB_DIR = "images";
    private final FileService fileService;
    private final ProfileImageDao profileImageDao;

    public void uploadImage(ProfileImageDto profileImageDto) {
        String fileName = fileService.saveUploadedFile(profileImageDto.getFile(),SUB_DIR );
        ProfileImage pi=ProfileImage.builder()
                .userId(profileImageDto.getUserId())
                .fileName(fileName)
                .id(profileImageDto.getId())
                .build();
        profileImageDao.save(pi);

    }
    public ResponseEntity<?> downloadImage(int imageId){
        ProfileImage profileImage = profileImageDao.getImageById(imageId);
        String filename = profileImage.getFileName();
        return fileService.getOutputFile(filename,"/images", MediaType.IMAGE_PNG);
    }
}
