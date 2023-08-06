package com.example.demo.model;

import com.example.demo.enums.AccountType;
import lombok.*;


@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)

public class User {
    private  int id;
    private String accountName;
    private String email;
    private String password;
    private String phoneNumber;
    private int roleId;
    private Boolean enabled;

}
