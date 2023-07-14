package com.example.demo.model;

import com.example.demo.enums.AccountType;
import lombok.Data;


@Data

public class User {
    private  int id;
    private String accountName;
    private String email;
    private AccountType accountType;
    private String password;
    private String phone_number;
    private String profile_photo;
}
