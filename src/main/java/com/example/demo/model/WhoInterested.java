package com.example.demo.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;



@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)

public class WhoInterested {
    private int applicant_id;
    private int job_resume_id;
    private User applicant;
    private JobResume job_resume;
    private LocalDateTime date;
}
