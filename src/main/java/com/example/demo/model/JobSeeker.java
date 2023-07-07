package com.example.demo.model;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)

public class JobSeeker {
    private User author;
    private String job;
    private Integer expectedSalary;
    private String jobExperience;
    private String education;
    private List<Contacts> contacts;
}
