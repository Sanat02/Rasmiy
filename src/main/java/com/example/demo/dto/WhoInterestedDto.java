package com.example.demo.dto;

import com.example.demo.model.JobResume;
import com.example.demo.model.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class WhoInterestedDto {
    private int id;
    private User applicant;
    private JobResumeDto job_resume;
    private LocalDateTime date;

}
