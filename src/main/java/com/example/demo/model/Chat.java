package com.example.demo.model;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Chat {
    private int id;
    private int userId;
    private int employerId;
    private String message;
    private LocalDate messageDate;
}
