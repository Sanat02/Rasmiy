package com.example.demo.dto;

import com.example.demo.enums.AccountType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private  int id;
    private String account_name;
    private String email;
    private AccountType account_type;
    private String password;
    private String phone_number;
    private String profile_photo;
}
