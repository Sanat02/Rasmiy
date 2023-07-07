package com.example.demo.model;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)

public class WhoInterested {
    private JobSeeker whoIsInterested;
    private Employer whatIsInterested;
    private Date date;
}
