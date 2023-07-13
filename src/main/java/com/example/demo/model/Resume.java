package com.example.demo.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data



public class Resume {
    private int id;
    private int user_id;
    private String job;
    private Integer expected_salary;
    private String job_experience;
    private String education;
}
