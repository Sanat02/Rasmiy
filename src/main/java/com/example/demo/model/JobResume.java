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
    private int user_id;
    private String job_tile;
    private Integer salary;
    private String job_description;
    private Integer experience;
    private String category;
}
