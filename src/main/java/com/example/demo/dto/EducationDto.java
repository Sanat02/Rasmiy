package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder

public class EducationDto {
    private String institution_name;
    private LocalDate start_date;
    private LocalDate end_date;
    private String degree;
}
