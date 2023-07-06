package com.example.demo.model;

import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)

public class Employer {
    private String jobTitle;
    private Integer salary;
    private String jobDescription;
    private Integer experience;
    private String category;
}
