package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder

public class EducationDto {
    private int id;
    private String institutionName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String degree;
}
