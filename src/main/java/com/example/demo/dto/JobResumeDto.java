package com.example.demo.dto;

import com.example.demo.model.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JobResumeDto {
    private int id;
    private UserDto user;
    private String jobTitle;
    private Integer salary;
    private String jobDescription;
    private Integer experience;
    private CategoryDto category;
}
