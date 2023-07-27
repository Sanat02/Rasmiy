package com.example.demo.dto;

import com.example.demo.model.JobResume;
import com.example.demo.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
public class WhoInterestedDto {
    private int id;
    private UserDto applicant;
    private JobResumeDto job_resume;

    //system sets the date automatically
    @JsonIgnore
    private LocalDate date;

}
