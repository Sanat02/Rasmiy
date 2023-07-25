package com.example.demo.dto;

import com.example.demo.model.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
@Data
@Builder
public class JobListDto {
    private int id;
    private User publisher;
    private int categoryId;
    private LocalDate date;
}
