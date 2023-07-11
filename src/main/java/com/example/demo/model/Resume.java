package com.example.demo.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data



public class Resume {
    private int id;
    private User user;
    private String job;
    private Integer expectedSalary;
    private String jobExperience;
    private String education;
    private List<Contacts> contacts=new ArrayList<>();


}
