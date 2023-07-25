package com.example.demo.model;

import lombok.*;

import java.time.LocalDate;
@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class JobExperience {
    private int id;
    private int resumeId;
    private String position;
    private LocalDate startDate;
    private LocalDate endDate;
}
