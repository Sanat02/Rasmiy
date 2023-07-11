package com.example.demo.model;

import lombok.*;

import java.time.LocalDateTime;



@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)

public class WhoInterested {
    private User applicant;
    private JobResume jobResume;
    private LocalDateTime date;
}
