package com.example.demo.dto;

import com.example.demo.enums.AccountType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private  int id;
    private String accountName;
    private String email;
    private AccountType accountType;
    private String password;
    private String phoneNumber;

}
