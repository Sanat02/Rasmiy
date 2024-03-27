package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity
@Table(name = "universities")
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String universityNameKg;
    private String universityNameRu;
    @Column(name = "affixed_university_i")
    private String affixedUniversityI;

    @Column(name = "affixed_university_b")
    private String affixedUniversityB;

    @Column(name = "affixed_university_t")
    private String affixedUniversityT;

    @Column(name = "affixed_university_j")
    private String affixedUniversityJ;

    @Column(name = "affixed_university_c")
    private String affixedUniversityC;
}
