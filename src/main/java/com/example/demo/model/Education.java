package com.example.demo.model;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder

public class Education {
    private int id;
    private int resumeId;
    private String institutionName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String degree;
}
