package com.example.demo.dto;

import com.example.demo.enums.AccountType;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserDto {
    private int id;
    private String accountName;

    @Email
    @NotEmpty
    private String email;


    @Size(min = 10, max = 15, message = "Phone number must be between 10 and 15 characters")
    private String phoneNumber;

    @NotBlank
    @Size(min = 6, max = 24, message = "Length must be >= 4 and <= 24")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).+$", message = "Password should contain at least one uppercase letter, one number")
    private String password;


    private String resetPasswordToken;


}
