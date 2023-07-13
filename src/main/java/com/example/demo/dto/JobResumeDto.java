package com.example.demo.dto;

import com.example.demo.model.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JobResumeDto {
    private int id;
    private User employer;
    private String job_tile;
    private Integer salary;
    private String job_description;
    private Integer experience;
    private String category;
}
