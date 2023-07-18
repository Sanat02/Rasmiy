package com.example.demo.model;

import lombok.*;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class ProfileImage {
    private int id;
    private long userId;
    private String fileName;

}
