package com.example.demo.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder

public class Resume {
    private int id;
    private int userId;
    private String job;
    private Integer expectedSalary;
}
