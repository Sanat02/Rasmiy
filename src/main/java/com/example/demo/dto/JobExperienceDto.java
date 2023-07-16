package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class JobExperienceDto {
    private String position;
    private LocalDate start_date;
    private LocalDate end_date;
}