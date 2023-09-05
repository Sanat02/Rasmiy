package com.example.demo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Sort;

import java.util.Date;

@Data
@Builder
public class JobResumeDto {
    private int id;
    private UserDto user;
    private String jobTitle;


    @Positive(message = "Salary must be a positive value")
    private Integer salary;


    private String jobDescription;


    @Min(value = 0, message = "Experience must not be less than 0")
    private Integer experience;


    private String category;

    private Date date;

}
