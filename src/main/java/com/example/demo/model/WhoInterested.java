package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;



@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
@Table(name = "who_interested")

public class WhoInterested {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int applicantId;
    private int jobResumeId;
    private LocalDate date;
}
