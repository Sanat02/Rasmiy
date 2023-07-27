package com.example.demo.dto;

import com.example.demo.annotations.EnumValidator;
import com.example.demo.enums.ContactType;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ContactDto {
    @NotNull
    @EnumValidator(enumClass = ContactType.class)
    private ContactType type;

    private String value;
}
