package com.example.demo.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)

public class JobResume {
    private int id;
    private int userId;
    private String jobTitle;
    private Integer salary;
    private String jobDescription;
    private Integer experience;
    private int categoryId;
}
