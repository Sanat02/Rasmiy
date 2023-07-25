package com.example.demo.dto;

import com.example.demo.model.Contacts;
import com.example.demo.model.Education;
import com.example.demo.model.JobExperience;
import com.example.demo.model.User;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder

public class ResumeDto {
    private int id;
    private UserDto applicant;
    private String job;
    private Integer expectedSalary;
    private JobExperienceDto jobExperience;
    private EducationDto education;
    private List<ContactDto> contacts;

}
