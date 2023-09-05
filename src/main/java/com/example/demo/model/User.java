package com.example.demo.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    private String accountName;
    private String email;
    private String password;
    private String phoneNumber;
    private int roleId;
    private Boolean enabled;
    @OneToMany(fetch= FetchType.LAZY,mappedBy = "user")
    List<Resume> resumes;

}
