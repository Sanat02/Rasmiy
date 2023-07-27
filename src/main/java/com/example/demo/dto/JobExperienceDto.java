package com.example.demo.dto;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class JobExperienceDto {
    private int id;

    @Pattern(regexp = "^[^0-9]*$", message = "Position should not contain numbers")
    private String position;

    @Past(message = "Start date must be in the past")
    private LocalDate startDate;

    @Past(message = "Start date must be in the past")
    private LocalDate endDate;
}
