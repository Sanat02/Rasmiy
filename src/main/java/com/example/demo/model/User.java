package com.example.demo.model;

import lombok.*;


@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class User {
    private String accountName;
    private String email;
    private String accountType;
    private String password;
}
