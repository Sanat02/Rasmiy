package com.example.demo.model;

import com.example.demo.enums.AccountType;
import lombok.*;


@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class User {
    private String accountName;
    private String email;
    private AccountType accountType;
    private String password;
}
