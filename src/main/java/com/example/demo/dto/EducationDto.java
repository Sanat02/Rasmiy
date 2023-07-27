package com.example.demo.dto;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder

public class EducationDto {
    private int id;

    @Pattern(regexp = "^[^0-9]*$", message = "Institution name should not contain numbers!")
    private String institutionName;

    @Past(message = "Start date must be in the past!")
    private LocalDate startDate;

    @Past(message = "End date must be in the past!")
    private LocalDate endDate;

    @Pattern(regexp = "^[^0-9]*$", message = "Degree should not contain numbers!")
    private String degree;
}
