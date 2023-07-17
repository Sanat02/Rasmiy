package com.example.demo.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)

public class Education {
    private int id;
    private int resumeId;
    private String institutionName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String degree;
}
