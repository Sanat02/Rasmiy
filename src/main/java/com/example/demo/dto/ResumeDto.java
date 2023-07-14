package com.example.demo.dto;

import com.example.demo.model.Contacts;
import com.example.demo.model.User;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder

public class ResumeDto {
    private int id;
    private User applicant;
    private String job;
    private Integer expectedSalary;
//    private String jobExperience;
//    private String education;
    private List<Contacts> contacts=new ArrayList<>();

}
