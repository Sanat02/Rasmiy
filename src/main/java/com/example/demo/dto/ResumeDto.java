package com.example.demo.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder

public class ResumeDto {
    private int id;
    private UserDto applicant;

    @Pattern(regexp = "^[^0-9]*$", message = "job should not contain numbers!")
    private String job;

    @Positive(message = "Salary must be a positive value")
    @Min(value = 0, message = "Experience must not be less than 0")
    private Integer expectedSalary;
    private JobExperienceDto jobExperience;
    private EducationDto education;
    private List<ContactDto> contacts;

}
