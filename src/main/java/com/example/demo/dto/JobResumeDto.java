package com.example.demo.dto;

import com.example.demo.model.Category;
import com.example.demo.model.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JobResumeDto {
    private int id;
    private User employer;
    private String jobTitle;
    private Integer salary;
    private String jobDescription;
    private Integer experience;
    private CategoryDto category;
}
