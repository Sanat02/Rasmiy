package com.example.demo.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;


@Builder
@Data
public class WhoInterestedDto {
    private int id;
    private UserDto applicant;
    private JobResumeDto job_resume;


    @JsonIgnore
    private LocalDate date;

}
