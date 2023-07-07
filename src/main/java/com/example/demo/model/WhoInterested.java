package com.example.demo.model;

import lombok.*;

import java.time.LocalDateTime;



@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)

public class WhoInterested {
    private User whoIsInterested;
    private JobList whatIsInterested;
    private LocalDateTime date;
}
