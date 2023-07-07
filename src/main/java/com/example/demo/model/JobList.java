package com.example.demo.model;

import lombok.*;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)

public class JobList {
    private User user;
    private String category;
    private LocalDateTime date;
}
